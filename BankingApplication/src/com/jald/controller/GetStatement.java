package com.jald.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jald.model.Model;
@WebServlet("/GetStatement")
public class GetStatement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		HttpSession session = request.getSession();     
		int accno = (int) session.getAttribute("accno");
		try {
			Model m = new Model();
			m.setAccno(accno);
			@SuppressWarnings("rawtypes")
			Map data=m.getStatement();
			if(data.isEmpty()==true) {
				response.sendRedirect("/BankingApplication/GetStatementFail.html");
			}else{
				session.setAttribute("sdata",m.Statementdata);
				session.setAttribute("accno",m.getAccno());
				response.sendRedirect("/BankingApplication/GetStatementSuccess.jsp");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}catch(Exception e) {
		response.sendRedirect("/BankingApplication/Login.html");
	}
	}

}
