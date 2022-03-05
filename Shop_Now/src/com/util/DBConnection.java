package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static Connection con = null;
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if(con == null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "root1427");
			System.out.println("----Connected-----");
		}
		return con;
	}

}
