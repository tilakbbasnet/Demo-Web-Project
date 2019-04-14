package com.test.demoapp.utility;

import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExcelGenerator {
	static final Logger LOGGER = Logger.getLogger(ExcelGenerator.class);
	private String query;
	private String outputFileLocation;
	private String outputFileName;
	private Workbook workBook;
	QueryRunner queryRunner;
	
	@SuppressWarnings("unused")
	private ExcelGenerator(String query, String outputFileLocation, String outputFileName) {
		this.query  = query;
		this.outputFileLocation = outputFileLocation;
		this.outputFileName = outputFileName;
	}

	public ExcelGenerator() {
		this.query="SELECT ID,NAME,EMAIL,GENDER,AGE,SALARY FROM EMPLOYEE";
		this.outputFileLocation = "";
		this.outputFileName = "";
		LOGGER.info("Excel Generator Initialized");		
	}

	public void getExcelOfQuery(String filename) {
		if(query == null || ("").equals(query)) {
			this.query = "SELECT SYSDATE() FROM DUAL";
		}

		if(outputFileName == null || ("").equals(outputFileName)) {
			outputFileName = "Excel-" +(System.nanoTime())+".xls";
		}

		if(outputFileLocation == null || ("").equals(outputFileLocation)) {
			outputFileName = "Excel-" +(System.nanoTime())+".xls";
		}

		try {
			List<Map<String, String>> resultSetMapList = queryMapList(this.query, new Object[] {});
			if(resultSetMapList.isEmpty()) {
				throw new SQLException("Empty Result Set");
			}
			String[] columns = (String[]) resultSetMapList.get(0).keySet().toArray(new String[resultSetMapList.get(0).size()]);
			workBook = new HSSFWorkbook();
			Sheet sheet = workBook.createSheet("Employee List");
			Row titleRow = sheet.createRow(1);
			Cell titleCell = titleRow.createCell(2);
			titleCell.setCellValue("Employee List");
			int row = 3;
			//Setting headers
			Row headerRow = sheet.createRow(row);
			for(int i=0; i < columns.length; i++) {
				headerRow.createCell(i).setCellValue(columns[i]);
			}

			//Setting filter
			sheet.setAutoFilter(new CellRangeAddress(0, 0, 0, columns.length - 1));

			for(Map<String, String> dataRow : resultSetMapList) {
				Row excelDataRow = sheet.createRow(++row);
				for(int i=0; i < columns.length; i++) {
					excelDataRow.createCell(i).setCellValue((String) dataRow.get(columns[i]));
				}
			}

			FileOutputStream fileOutputStream;
			try {
				fileOutputStream = new FileOutputStream(System.getProperty("user.dir")+outputFileName);
				workBook.write(fileOutputStream);
				fileOutputStream.close();
			}catch (Exception e) {
				LOGGER.info("Dumping to excel file location "+outputFileLocation);
				LOGGER.error("Cannot generate excel file",e);
			}

		}catch (Exception e) {
			LOGGER.error("Error during excel generation for query :::::::::::::::  "+this.query,e);
		}
	}
	
	
	public List<Map<String, String>> queryMapList(String query, Object...params) throws SQLException{
		List<Map<String, String>> map=null;
		DBConnection db = new DBConnection();
		try {
		queryRunner = new QueryRunner();
		map = queryRunner.query(db.con, query, listOfMapString , params);
		LOGGER.info("Total employee records fetched");
		}catch (Exception e) {
			LOGGER.error("Error during retrieving list of map by Query Runner ",e);
		}finally {
			db.endConnection();
		}
		return map;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	ResultSetHandler<List<Map<String, String>>> listOfMapString = new ResultSetHandler<List<Map<String,String>>>() {
    public List<Map<String, String>> handle(ResultSet rs) throws SQLException {
			ResultSetMetaData metadata = rs.getMetaData();
			List<Map<String, String>> result = new ArrayList();
			int totalColumn = metadata.getColumnCount();
			String[] columns = new String[totalColumn];
			for(int i=1; i <= totalColumn; i++) {
				columns[i-1] = metadata.getColumnName(i);
			}
			while(rs.next()) {
				Map<String, String> data = new HashMap();
				for(String columnName : columns) {
					data.put(columnName,rs.getString(columnName));
				}
				result.add(data);
			}
			return result;
		}
	};
}
