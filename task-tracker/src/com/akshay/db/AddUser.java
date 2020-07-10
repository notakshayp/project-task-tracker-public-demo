package com.akshay.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddUser {
	public boolean addUser(String username, String password) {
		DatabaseOps db = new DatabaseOps();
		Connection con = db.connectDB();
		if (con == null) {
			return false;
		} else {

			String statement = "INSERT INTO public.\"userTable\" (username,password) VALUES (?,?);";
			try {
				PreparedStatement query = con.prepareStatement(statement);
				query.setString(1, username);
				query.setString(2, password);
				int result = query.executeUpdate();

				query.close();

				con.close();

				if (result == 1) {
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
