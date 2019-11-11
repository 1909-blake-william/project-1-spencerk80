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

	private static final long serialVersionUID = -1955927604198436588L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ObjectMapper om = new ObjectMapper();
		User credentials = (User) om.readValue(req.getReader(), User.class);
		User loggedInUser = null;
		
		if("/ReimbursementSystem/auth/login".contentEquals(req.getRequestURI())) {
		
			try {
				
				loggedInUser = UserDao.currentImplementation.lookup(credentials.getUsername(), credentials.getPassword());
				
			} catch(SQLException e) {
				
				System.err.println(e.getMessage());
				resp.setStatus(401);
				return;
				
			}
			
			if (loggedInUser == null) {
				
				resp.setStatus(401); // Unauthorized status code
				return;
				
			} else {
				
				resp.setStatus(201);
				req.getSession().setAttribute("user", loggedInUser);
				resp.getWriter().write(om.writeValueAsString(loggedInUser));
				return;
				
			}
			
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ObjectMapper om;
		String json;
		
		if ("/PokemonApi/auth/session-user".equals(req.getRequestURI())) {
			
			om = new ObjectMapper();
			json = om.writeValueAsString(req.getSession().getAttribute("user"));
			resp.getWriter().write(json);
			
		}
		
	}	

}
