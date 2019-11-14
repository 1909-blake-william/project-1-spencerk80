package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.Status;
import com.revature.models.Reimbursement;

public interface ReimbursementDao {

	ReimbursementDao currentImplementation = ReimbursementDaoSql.instance;
	
	List<Reimbursement> findall() throws SQLException;
	List<Reimbursement> findByName(String username, String status) throws SQLException;
	List<Reimbursement> findByStatus(Status status) throws SQLException;
	int write(Reimbursement r) throws SQLException;
	int updateStatus(Reimbursement r, String setTo, String resolver) throws SQLException;
	
}
