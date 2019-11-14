package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.Status;
import com.revature.models.Reimbursement;
import com.revature.util.ConnectionUtil;
import com.revature.util.SafeParser;

public class ReimbursementDaoSql implements ReimbursementDao {
	
	public static final ReimbursementDaoSql instance = new ReimbursementDaoSql();
	
	private ReimbursementDaoSql() {}

	public List<Reimbursement> findall() throws SQLException {
	
		List<Reimbursement> list	= new ArrayList<>();
		PreparedStatement	ps;
		ResultSet			rs;
		
		try(Connection c = ConnectionUtil.getConnection()) {
			
			ps = c.prepareStatement("SELECT amount, description, submitted, resolved, u1.lastname || \', \' || u1.firstname AS author, u1.username, " +
									"u2.lastname || \', \' || u2.firstname AS resolver, reimbursement_status.status, reimbursement_type.type " +
									"FROM reimbursment INNER JOIN reimbursement_status " +
									"ON reimbursement_status.id = reimbursment.status LEFT JOIN reimbursement_type " +
									"ON reimbursement_type.id = reimbursment.type LEFT JOIN users u1 " +
									"ON u1.id = reimbursment.author LEFT JOIN users u2 " +
									"ON u2.id = reimbursment.resolver");
			rs = ps.executeQuery();
			
			while(rs.next())
				
				list.add(new Reimbursement(rs.getDouble("AMOUNT"), rs.getString("DESCRIPTION"), rs.getString("AUTHOR"), rs.getString("USERNAME"),
											rs.getString("RESOLVER").equals(", ") ? null : rs.getString("RESOLVER"),
											rs.getTimestamp("SUBMITTED"), rs.getTimestamp("RESOLVED"), 
											SafeParser.parseType(rs.getString("TYPE")), SafeParser.parseStatus(rs.getString("STATUS"))));
				
			
		} catch(SQLException e) {
			
			throw e;
			
		}
		
		return list;
		
	}
	
	public List<Reimbursement> findByStatus(Status status) throws SQLException {
		
		List<Reimbursement> list	= new ArrayList<>();
		PreparedStatement	ps;
		ResultSet			rs;
		
		try(Connection c = ConnectionUtil.getConnection()) {
			
			ps = c.prepareStatement("SELECT amount, description, submitted, resolved, u1.lastname || \', \' || u1.firstname AS author, u1.username, " +
									"u2.lastname || \', \' || u2.firstname AS resolver, reimbursement_status.status, reimbursement_type.type " +
									"FROM reimbursment INNER JOIN reimbursement_status " +
									"ON reimbursement_status.id = reimbursment.status LEFT JOIN reimbursement_type " +
									"ON reimbursement_type.id = reimbursment.type LEFT JOIN users u1 " +
									"ON u1.id = reimbursment.author LEFT JOIN users u2 " +
									"ON u2.id = reimbursment.resolver " +
									"WHERE reimbursment.status = (SELECT id FROM reimbursement_status " +
									"WHERE status = ?)");
			
			ps.setString(1, status.toString());
			rs = ps.executeQuery();
			
			while(rs.next())
				
				list.add(new Reimbursement(rs.getDouble("AMOUNT"), rs.getString("DESCRIPTION"), rs.getString("AUTHOR"), rs.getString("USERNAME"), 
											rs.getString("RESOLVER").equals(", ") ? null : rs.getString("RESOLVER"),
											rs.getTimestamp("SUBMITTED"), rs.getTimestamp("RESOLVED"), 
											SafeParser.parseType(rs.getString("TYPE")), SafeParser.parseStatus(rs.getString("STATUS"))));
				
			
		} catch(SQLException e) {
			
			throw e;
			
		}
		
		return list;
		
	}
	
	@Override
	public List<Reimbursement> findByName(String username) throws SQLException {
		
		List<Reimbursement> list	= new ArrayList<>();
		PreparedStatement	ps;
		ResultSet			rs;
		
		try(Connection c = ConnectionUtil.getConnection()) {
			
			ps = c.prepareStatement("SELECT amount, description, submitted, resolved, u1.lastname || \', \' || u1.firstname AS author, u1.username, " +
									"u2.lastname || \', \' || u2.firstname AS resolver, reimbursement_status.status, reimbursement_type.type " +
									"FROM reimbursment INNER JOIN reimbursement_status " +
									"ON reimbursement_status.id = reimbursment.status LEFT JOIN reimbursement_type " +
									"ON reimbursement_type.id = reimbursment.type LEFT JOIN users u1 " +
									"ON u1.id = reimbursment.author LEFT JOIN users u2 " +
									"ON u2.id = reimbursment.resolver " +
									"WHERE author = (SELECT id FROM users WHERE username = ?)");
			
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			while(rs.next())
				
				list.add(new Reimbursement(rs.getDouble("AMOUNT"), rs.getString("DESCRIPTION"), rs.getString("AUTHOR"), rs.getString("USERNAME"), 
											rs.getString("RESOLVER").equals(", ") ? null : rs.getString("RESOLVER"),
											rs.getTimestamp("SUBMITTED"), rs.getTimestamp("RESOLVED"), 
											SafeParser.parseType(rs.getString("TYPE")), SafeParser.parseStatus(rs.getString("STATUS"))));
				
			
		} catch(SQLException e) {
			
			throw e;
			
		}
		
		return list;		
		
	}
	
	@Override
	public List<Reimbursement> findByName(String username, String status) throws SQLException {
		
		List<Reimbursement> list	= new ArrayList<>();
		PreparedStatement	ps;
		ResultSet			rs;
		
		try(Connection c = ConnectionUtil.getConnection()) {
			
			ps = c.prepareStatement("SELECT amount, description, submitted, resolved, u1.lastname || \', \' || u1.firstname AS author, u1.username, " +
									"u2.lastname || \', \' || u2.firstname AS resolver, reimbursement_status.status, reimbursement_type.type " +
									"FROM reimbursment INNER JOIN reimbursement_status " +
									"ON reimbursement_status.id = reimbursment.status LEFT JOIN reimbursement_type " +
									"ON reimbursement_type.id = reimbursment.type LEFT JOIN users u1 " +
									"ON u1.id = reimbursment.author LEFT JOIN users u2 " +
									"ON u2.id = reimbursment.resolver " +
									"WHERE reimbursment.status = (SELECT id FROM reimbursement_status " +
									"WHERE reimbursement_status.status = ?) AND author = (SELECT id FROM users WHERE username = ?)");
			
			ps.setString(1, status);
			ps.setString(2, username);
			rs = ps.executeQuery();
			
			while(rs.next())
				
				list.add(new Reimbursement(rs.getDouble("AMOUNT"), rs.getString("DESCRIPTION"), rs.getString("AUTHOR"), rs.getString("USERNAME"), 
											rs.getString("RESOLVER").equals(", ") ? null : rs.getString("RESOLVER"),
											rs.getTimestamp("SUBMITTED"), rs.getTimestamp("RESOLVED"), 
											SafeParser.parseType(rs.getString("TYPE")), SafeParser.parseStatus(rs.getString("STATUS"))));
				
			
		} catch(SQLException e) {
			
			throw e;
			
		}
		
		return list;		
		
	}

	@Override
	public int write(Reimbursement r) throws SQLException {
		
		PreparedStatement 	ps;
		
		try(Connection c = ConnectionUtil.getConnection()) {
			
			ps = c.prepareStatement("INSERT INTO reimbursment " + 
									"VALUES (rmbsmnt_id_seq.NEXTVAL, ?, SYSDATE, NULL, ?, NULL, " + 
									"(SELECT id FROM users WHERE username = ?), " + 
									"NULL, (SELECT id FROM reimbursement_status WHERE status = ?), " + 
									"(SELECT id FROM reimbursement_type WHERE type = ?))");
			
			ps.setDouble(1, r.getAmount());
			ps.setString(2, r.getDescription());
			ps.setString(3, r.getUsername());
			ps.setString(4, r.getStatus().toString());
			ps.setString(5, r.getType().toString());
			
			return ps.executeUpdate();
			
		} catch(SQLException e) {
			
			throw e;
			
		}
		
	}

	@Override
	public int updateStatus(Reimbursement r, String setTo, String resolver) throws SQLException {
	
		PreparedStatement 	ps;
		
		try(Connection c = ConnectionUtil.getConnection()) {
			
			ps = c.prepareStatement("UPDATE reimbursment " + 
									"SET status = ( " + 
									"    SELECT id FROM reimbursement_status " + 
									"    WHERE status = ? " + 
									"), resolved = SYSDATE, " + 
									"resolver = ( " +
									"	 SELECT id FROM users " +
									" 	 WHERE username = ? " +
									") " +
									"WHERE submitted = ? " + 
									"AND author = ( " + 
									"    SELECT id FROM users " + 
									"    WHERE username = ? " + 
									")");
			
			ps.setString(1, SafeParser.parseStatus(setTo).toString());
			ps.setString(2, resolver);
			ps.setTimestamp(3, r.getSubmitted());
			ps.setString(4, r.getUsername());
			
			return ps.executeUpdate();
			
		} catch(SQLException e) {
			
			throw e;
			
		}
		
	}

}
