package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pojo.UserModel;
import com.util.DBConnection;

public class UserDao {

	private static Connection con;

	public UserDao(Connection con) {
		this.con = con;
	}

	public UserModel userLogin(String email, String password) throws SQLException {

		try {
			String sql = "select * from user where email=? and pass=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return new UserModel(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
						rs.getString("pass"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}

}
