package com.jald.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jald.model.Model;
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
   
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Collecting the data from Login.html
		String custid=request.getParameter("custid");
		String pwd=request.getParameter("pwd");
		//creating the session to store account number
		session = request.getSession();
		
		try {
			Model m = new Model();
			m.setCustid(custid);
			m.setPwd(pwd);
			boolean b=m.logIn();
			if(b==true) {
				//store account number and send response to Home.html
				session.setAttribute("accno",m.getAccno());
				response.sendRedirect("/BankingApplication/Home.html");
			}
			else {
				response.sendRedirect("/BankingApplication/Error.html");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
