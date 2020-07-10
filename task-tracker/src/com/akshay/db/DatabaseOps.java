package com.akshay.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseOps {

	public Connection connectDB() {
		Connection c = null;

		try {
			Class.forName("org.postgresql.Driver");
			String host = "host here";
			String port = "port here";
			String db = "dp name here";
			String user = "username";
			String pass = "password";
			String url = "jdbc:postgresql://" + host + ":" + port + "/" + db;
			c = DriverManager.getConnection(url, user, pass);
			return c;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return c;

		}
	}

}