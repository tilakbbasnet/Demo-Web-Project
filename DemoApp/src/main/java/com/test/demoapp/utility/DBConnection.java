package com.test.demoapp.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

//@Repository("db")
public class DBConnection {
	private final Logger LOGGER = Logger.getLogger(DBConnection.class);
	public Connection con=null;

	public DBConnection() {
		getConnection();
	}

	public void getConnection() { 
		try{  
			Class.forName("com.mysql.jdbc.Driver");
			//database = 'testdb', user = 'root', and password = 'Stilak1@'

			//default application login information =>  USERNAME=test,  PASSWORD='test123'
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb","root","Stilak1@");
		}catch(Exception e){
			LOGGER.error("Cannot establish database connection ",e);
		}  
	}  	

	public void endConnection() {
		try {
			if(con != null) {
				con.close();
			}}catch (Exception e) {
				LOGGER.error("Cannot close database connection ",e);
			}
	}
}
