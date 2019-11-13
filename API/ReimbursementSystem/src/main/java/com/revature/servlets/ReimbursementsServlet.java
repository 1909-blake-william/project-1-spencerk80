package com.revature.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.util.SafeParser;
import com.revature.dao.ReimbursementDao;

public class ReimbursementsServlet extends HttpServlet {
	
	private static final int	INTERNAL_FAILURE	= 500,
								BAD_REQUEST			= 400,
								SUCCESS				= 201;
	private static final long 	serialVersionUID 	= 3826173837422498900L;
	
	ObjectMapper 				om 					= new ObjectMapper();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.addHeader("Access-Control-Allow-Origin", "http://localhost:5500");
		resp.addHeader("Access-Control-Allow-Methods", "POST, GET, PATCH, OPTIONS, PUT, DELETE, HEAD");
		resp.addHeader("Access-Control-Allow-Headers",
						"Origin, Methods, Credentials, X-Requested-With, Content-Type, Accept");
		resp.addHeader("Access-Control-Allow-Credentials", "true");
		resp.setContentType("application/json");
		
		if(req.getMethod().equalsIgnoreCase("PATCH"))
			
			doPatch(req, resp);
		
		else
			
			super.service(req, resp);
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Reimbursement>	reimbursements;
		String				json;
		
		try {
			
			if(req.getParameter("status") != null) 
				
				reimbursements = ReimbursementDao.currentImplementation.findByStatus(SafeParser.parseStatus(req.getParameter("status")));
				
			else if(req.getParameter("name") != null)
				
				reimbursements = ReimbursementDao.currentImplementation.findByName(req.getParameter("name"));
			
			else
				
				reimbursements = ReimbursementDao.currentImplementation.findall();
			
		} catch(SQLException e) {
			
			resp.setStatus(INTERNAL_FAILURE);
			resp.getWriter().write(e.getMessage());
			System.err.println(e.getMessage());
			return;
			
		}
		
		json = om.writeValueAsString(reimbursements);
		resp.addHeader("content-type", "application/json");
		resp.getWriter().write(json);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Reimbursement		reimbursement;
		
		try { 
		
			reimbursement = om.readValue(req.getReader(), Reimbursement.class);
			
			if(ReimbursementDao.currentImplementation.write(reimbursement) > 0)
				
				resp.setStatus(SUCCESS);
			
			else
				
				resp.setStatus(BAD_REQUEST);
		
		} catch(Exception e) {
		
			resp.setStatus(INTERNAL_FAILURE);
			System.err.println(e.getMessage());
			
		}
		
	}
	
	protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Reimbursement		reimbursement;
		String				setTo				= req.getParameter("set"),
							resolver			= req.getParameter("resolver");
		
		try {
			
			if(setTo == null || resolver == null) {
				
				resp.setStatus(BAD_REQUEST);
				return;
				
			}
			
			reimbursement = om.readValue(req.getReader(), Reimbursement.class);
			
			if(ReimbursementDao.currentImplementation.updateStatus(reimbursement, setTo, resolver) > 0) 
				
				resp.setStatus(SUCCESS);
			
			else
				
				resp.setStatus(BAD_REQUEST);
			
		} catch(Exception e) {
			
			resp.setStatus(INTERNAL_FAILURE);
			System.err.println(e.getMessage());
			
		}
		
	}

}
