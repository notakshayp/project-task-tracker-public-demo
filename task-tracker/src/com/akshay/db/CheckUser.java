package com.akshay.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckUser {
	public boolean isUser(String username) {
		DatabaseOps db = new DatabaseOps();
		Connection con = db.connectDB();
		if (con == null) {
			return false;
		} else {

			String statement = "select * from public.\"userTable\" WHERE (username=?)";
			try {
				PreparedStatement query = con.prepareStatement(statement);
				query.setString(1, username);
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