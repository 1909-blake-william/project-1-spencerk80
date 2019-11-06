package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	public static Connection getConnection() throws SQLException {
		
		String		url			= System.getenv("REIMBURSE_DB_URL"),
					username	= System.getenv("REIMBURSE_DB_USERNAME"),
					password	= System.getenv("REIMBURSE_DB_PASSWD");
		
		return DriverManager.getConnection(url, username, password);
		
	}

}
