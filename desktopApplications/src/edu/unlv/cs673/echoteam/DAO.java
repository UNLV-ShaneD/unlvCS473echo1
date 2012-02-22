package edu.unlv.cs673.echoteam;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DAO{
	static private String dbUrl = "";
	public String query = "";
	static private String user = "";
	static private String password = "";
	public Connection connection = null;
	
	/**
	 * Creates DB connection
	 */
	public DAO() {
		// Get db connection info from property file.
		Properties prop = new Properties();
		try {
			// load a properties file
			InputStream in = getClass().getResourceAsStream("/config.properties");
			prop.load(in);

			// get the property value and print it out
			dbUrl = prop.getProperty("dbUrl");
			user = prop.getProperty("user");
			password = prop.getProperty("password");

			// Connect to DB
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(dbUrl, user, password);
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Closes DB connection
	 */
	public void close(){
		try{
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Executes a query against the db and returns the record set to the caller.
	 * 
	 * @param query SQL that includes a select statement
	 * @return
	 */
	public ResultSet readQuery(String query) {
		// TODO: Add code verifying sql is of select type.
		this.query = query;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(this.query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
}
