package io.ztech.fitnessapplication.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Config {
	private static final String driverString = "com.mysql.cj.jdbc.Driver";
	private static final String dbConnString = "jdbc:mysql://localhost:3306/GoFitDB?useSSL=false";
	private static final String user = "root";
	private static final String password = "Ztech@123";

	public Connection getConnection() {
		try {
			Class.forName(driverString);
			Connection con = DriverManager.getConnection(dbConnString, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public void closeConnection(Connection conn, ResultSet rs, PreparedStatement ps) {
		try {
			if (conn != null) {
				conn.close();
			}
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
