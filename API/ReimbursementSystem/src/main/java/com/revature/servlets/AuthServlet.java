package com.revature.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.UserDao;
import com.revature.models.User;

public class AuthServlet extends HttpServlet {
	
	private final static int		INTERNAL_FAILURE		= 500,
									BAD_LOGIN 				= 401,
									GOOD_LOGIN 				= 201;
	private final static boolean 	CREATE_NEW_SESSION 		= true,
									GET_EXISTING_SESSION 	= false;
	private static final long 		serialVersionUID 		= -1955927604198436588L;
	
	ObjectMapper 					om 						= new ObjectMapper();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.addHeader("Access-Control-Allow-Origin", "http://localhost:5500");
		resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		resp.addHeader("Access-Control-Allow-Headers",
						"Origin, Methods, Credentials, X-Requested-With, Content-Type, Accept");
		resp.addHeader("Access-Control-Allow-Credentials", "true");
		resp.setContentType("application/json");
		
		super.service(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		User credentials = (User) om.readValue(req.getReader(), User.class);
		User loggedInUser = null;
		
		if("/ReimbursementSystem/auth/login".equals(req.getRequestURI())) {
		
			try {
				
				loggedInUser = UserDao.currentImplementation.lookup(credentials.getUsername(), credentials.getPassword());
				
			} catch(SQLException e) {
				
				System.err.println(e.getMessage());
				resp.setStatus(INTERNAL_FAILURE);
				return;
				
			}
			
			if (loggedInUser == null) {
				
				resp.setStatus(BAD_LOGIN);
				return;
				
			} else {
				
				resp.setStatus(GOOD_LOGIN);
				req.getSession(CREATE_NEW_SESSION).setAttribute("user", loggedInUser);
				resp.getWriter().write(om.writeValueAsString(loggedInUser));
				return;
				
			}
			
		}
			
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String json;
		
		if ("/ReimbursementSystem/auth/session-user".equals(req.getRequestURI())) {
			
			json = om.writeValueAsString(req.getSession(GET_EXISTING_SESSION).getAttribute("user"));
			resp.getWriter().write(json);
			
		}
		
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	if("/ReimbursementSystem/auth/logout".equals(req.getRequestURI())) {
		
		req.getSession(GET_EXISTING_SESSION).invalidate();
		
	}
		
	}

}
