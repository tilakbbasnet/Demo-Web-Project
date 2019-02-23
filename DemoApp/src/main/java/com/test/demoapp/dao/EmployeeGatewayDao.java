package com.test.demoapp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.test.demoapp.model.Employee;
import com.test.demoapp.utility.DBConnection;

public class EmployeeGatewayDao{

//	@Autowired
//	private DBConnection db;
	
	DBConnection db = new DBConnection();
	private static final Logger LOGGER = Logger.getLogger(EmployeeGatewayDao.class);
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;
	int result = 0;

	public boolean checkValidUser(String username, String password) {
		boolean isValid = false;
		DBConnection db = new DBConnection();
		try {
			pstmt = db.con.prepareStatement("SELECT COUNT(1) FROM USERS_INFO WHERE USERNAME=? AND PASSWORD=?");
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				isValid=rs.getInt(1) > 0 ?  true : false;
			}
		}catch (Exception e) {
			LOGGER.error("Error during user validation ",e);
		}finally {
			db.endConnection();
		}
		return isValid;
	}

	public boolean save(Employee emp) {
		boolean isSaved = false;
		DBConnection db = new DBConnection();
		try {
			//First if statement is needed only for test case execution
			//otherwise it has already been validated in front end (UI)
			if(emp.getAge() != 0 && emp.getSalary() != 0 && !emp.getGender().isEmpty() && !emp.getName().isEmpty() && !emp.getEmail().isEmpty()) {
				if(emp.getId() == 0) {
					pstmt = db.con.prepareStatement("INSERT INTO EMPLOYEE (NAME,EMAIL,AGE,GENDER,SALARY) VALUES (?,?,?,?,?)");
					pstmt.setString(1, emp.getName());
					pstmt.setString(2, emp.getEmail());
					pstmt.setInt(3, emp.getAge());
					pstmt.setString(4, emp.getGender());
					pstmt.setLong(5, emp.getSalary());
				}else {
					pstmt = db.con.prepareStatement("UPDATE EMPLOYEE SET NAME=?, EMAIL=?, AGE=?, GENDER=?, SALARY=? WHERE ID =?");
					pstmt.setString(1, emp.getName());
					pstmt.setString(2, emp.getEmail());
					pstmt.setInt(3, emp.getAge());
					pstmt.setString(4, emp.getGender());
					pstmt.setLong(5, emp.getSalary());
					pstmt.setLong(6, emp.getId());
				}
				result = pstmt.executeUpdate();
				if(result > 0) {
					isSaved = true;
				}
			}
		}catch (Exception e) {
			LOGGER.error("Error during employee registration ",e);
		}finally {
			db.endConnection();
		}
		return isSaved;
	}

	public List<Employee> findById(Long id) {
		List<Employee> employeeList = new ArrayList<Employee>();
		DBConnection db = new DBConnection();
		try {
			pstmt = db.con.prepareStatement("SELECT ID,NAME,EMAIL,GENDER,AGE,SALARY FROM EMPLOYEE WHERE ID=?");
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				employeeList.add(new Employee(rs.getLong("id"), rs.getString("name"), rs.getString("email"), rs.getInt("age"), rs.getString("gender"), rs.getLong("salary")));
			}
		}catch (Exception e) {
			LOGGER.error("Error during employee retrieval for given employee id ",e);
			return new ArrayList<Employee>();
		}finally {
			db.endConnection();
		}
		return employeeList;
	}

	public boolean delete(Long id) {
		boolean isDeleted = false;
		DBConnection db = new DBConnection();
		try {
			pstmt = db.con.prepareStatement("DELETE FROM EMPLOYEE WHERE ID=?");
			pstmt.setLong(1, id);
			result = pstmt.executeUpdate();
			if(result > 0) {
				isDeleted = true;
			}
		}catch (Exception e) {
			LOGGER.error("Error during provided employee deletion ",e);
		}finally {
			db.endConnection();
		}
		return isDeleted;
	}

	public List<Employee> findAll() {
		List<Employee> employeeList = new ArrayList<Employee>();
		DBConnection db = new DBConnection();
		try {
			stmt = db.con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM EMPLOYEE");
			while(rs.next()) {
				employeeList.add(new Employee(rs.getLong("id"), rs.getString("name"), rs.getString("email"), rs.getInt("age"), rs.getString("gender"), rs.getLong("salary")));
			}
		}catch (Exception e) {
			LOGGER.error("Error during all employee retrieval ",e);
		}finally {
			db.endConnection();
		}
		return employeeList;
	}

	public int count() {
		int totalemployee = 0;
		DBConnection db = new DBConnection();
		try {
			stmt = db.con.createStatement();
			rs = stmt.executeQuery("SELECT COUNT(1) FROM EMPLOYEE");
			if(rs.next()) {
				totalemployee = rs.getInt(1);
			}
		}catch (Exception e) {
			LOGGER.error("Error during total employee count retrieval ",e);
		}finally {
			db.endConnection();
		}
		return totalemployee;
	}

	public List<Employee> find(String subquery) {
		List<Employee> employeeList = new ArrayList<Employee>();
		DBConnection db = new DBConnection();
		String mainQuery = "SELECT * FROM EMPLOYEE ";
		try {
			stmt = db.con.createStatement();
			if(!subquery.trim().isEmpty() && subquery != null) {
				System.err.println("Inputted sub query is: "+subquery);
				mainQuery += " WHERE "+subquery.toUpperCase();
				System.err.println("Appended query is: "+mainQuery);
				rs = stmt.executeQuery(mainQuery);
				while(rs.next()) {
					employeeList.add(new Employee(rs.getLong("id"), rs.getString("name"), rs.getString("email"), rs.getInt("age"), rs.getString("gender"), rs.getLong("salary")));
				}
			}
		}catch (Exception e) {
			LOGGER.error("Error during employee retrieval with subquery provided by user ",e);
			return new ArrayList<Employee>();
		}finally {
			db.endConnection();
		}
		return employeeList;
	}
}
