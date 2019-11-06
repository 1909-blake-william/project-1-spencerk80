package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;

public class ReimbursementDaoSql implements ReimbursementDao {
	
	public static final ReimbursementDaoSql instance = new ReimbursementDaoSql();
	
	private ReimbursementDaoSql() {}

	public List<Reimbursement> findall() {
	
		List<Reimbursement> list = new ArrayList<>();
		
		
		
		return list;
		
	};

}
