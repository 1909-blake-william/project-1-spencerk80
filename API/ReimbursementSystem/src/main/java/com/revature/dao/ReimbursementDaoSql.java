package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.util.ConnectionUtil;

public class ReimbursementDaoSql implements ReimbursementDao {
	
	public static final ReimbursementDaoSql instance = new ReimbursementDaoSql();
	
	private ReimbursementDaoSql() {}

	public List<Reimbursement> findall() throws SQLException {
	
		List<Reimbursement> list	= new ArrayList<>();
		PreparedStatement	ps;
		ResultSet			rs;
		
		try(Connection c = ConnectionUtil.getConnection()) {
			
			ps = c.prepareStatement("***Write massive join querry here***");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				//Take the fields and construct an object
				//add that object to the list
				
			}
			
		}
		
		return list;
		
	};

}
