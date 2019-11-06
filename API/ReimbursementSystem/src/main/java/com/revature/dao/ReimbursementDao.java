package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbursementDao {

	ReimbursementDao currentImplementation = ReimbursementDaoSql.instance;
	
	List<Reimbursement> findall() throws SQLException;
	
}
