package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserDaoSql implements UserDao {
	
	public static final UserDaoSql	instance	= new UserDaoSql();
	
	private UserDaoSql() {super();}

	@Override
	public User lookup(String username, String password) throws SQLException {
		
		PreparedStatement	ps;
		ResultSet			rs;
		
		try(Connection c = ConnectionUtil.getConnection()) {
			
			ps = c.prepareStatement("SELECT username, lastname || ', ' || firstname AS fullname, password, user_role.role FROM users " +
									"INNER JOIN user_role ON users.role = user_role.id " +
									"WHERE username = ? AND password = ?");
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			
			if(rs.next())
				
				return new User(rs.getString("username"), rs.getString("fullname"), rs.getString("password"), rs.getString("role"));
			
			else
				
				return null;
			
		} catch(SQLException e) {
			
			throw e;
			
		}
		
	}

}
