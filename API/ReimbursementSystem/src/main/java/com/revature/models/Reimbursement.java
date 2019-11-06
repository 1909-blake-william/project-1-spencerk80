package com.revature.models;

import java.sql.Timestamp;

import com.revature.Status;
import com.revature.Type;

public class Reimbursement {

	private double 		amount;
			String		description,
						author,
						resolver;
			Timestamp	submitted,
						resolved;
			Type		type;
			Status		status;
	
}
