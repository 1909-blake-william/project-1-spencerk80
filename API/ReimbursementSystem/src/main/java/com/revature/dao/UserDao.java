package com.revature.dao;

import java.sql.SQLException;

import com.revature.models.User;

public interface UserDao {
	
	UserDao		currentImplementation 	= UserDaoSql.instance;
	
	User lookup(String username, String password) throws SQLException;

}
