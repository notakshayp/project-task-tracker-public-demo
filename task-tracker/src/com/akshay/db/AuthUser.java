package com.akshay.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthUser {
	public boolean verfiyUser(String username, String password) {
		DatabaseOps db = new DatabaseOps();
		Connection con = db.connectDB();
		if (con == null) {
			return false;
		} else {

			String statement = "select * from public.\"userTable\" WHERE (username=? AND password=?)";
			try {
				PreparedStatement query = con.prepareStatement(statement);
				query.setString(1, username);
				query.setString(2, password);
				ResultSet rs = query.executeQuery();
				con.close();
				if (rs.next()) {
					return true;
				} else {
					return false;
				}
			} catch (SQLException e) {
				return false;
			}

		}

	}
}
