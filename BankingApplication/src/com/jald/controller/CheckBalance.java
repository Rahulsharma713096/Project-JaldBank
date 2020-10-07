package com.jald.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jald.model.Model;
@WebServlet("/CheckBalance")
public class CheckBalance extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	       HttpSession session = req.getSession();
	        int accno = (int) session.getAttribute("accno");	
		try {
			Model m = new Model();
			m.setAccno(accno);
			
		boolean b=m.checkBalance();
		if(b==true) {
			//store account number and send response to Home.html
			session.setAttribute("bal",m.getBal());
			res.sendRedirect("/BankingApplication/BalanceView.jsp");
		}
		else {
			res.sendRedirect("/BankingApplication/BalanceFail.html");
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

   
}
