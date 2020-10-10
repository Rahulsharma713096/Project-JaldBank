package com.jald.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jald.model.Model;
@WebServlet("/Loan")
public class Loan extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("inside Loan");
try {
	HttpSession session = request.getSession();
	        int accno = (int) session.getAttribute("accno");
	        String Loantype = request.getParameter("loan");
	        System.out.println(Loantype);
	        
	         try {
	        	 Model m = new Model();
	        	 m.setAccno(accno);
				boolean b=m.applyLoan();
				 m.sendLoanData(Loantype);
				if(b==true) {
					session.setAttribute("name",m.getName());
					session.setAttribute("email",m.getEmail());
					response.sendRedirect("/BankingApplication/LoanSuccess.jsp");   
				}else {
					
					response.sendRedirect("/BankingApplication/LoanFail.html");
				}
				
			} catch (Exception e) {
				session.setAttribute("accno", accno);
				response.sendRedirect("/BankingApplication/Home.html");
			}
	
} catch (Exception e) {
	response.sendRedirect("/BankingApplication/Login.html");
}
		
		
		
	}

}
