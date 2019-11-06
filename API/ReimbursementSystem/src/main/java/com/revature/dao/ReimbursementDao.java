package com.revature.dao;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbursementDao {

	ReimbursementDao currentImplementation = ReimbursementDaoSql.instance;
	
	List<Reimbursement> findall();
	
}
