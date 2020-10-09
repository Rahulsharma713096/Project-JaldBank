package com.jald.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jald.model.Model;
@WebServlet("/Transfer")
public class Transfer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int accno = (int) session.getAttribute("accno");
		int amt=Integer.parseInt(request.getParameter("amt"));
		int raccno=Integer.parseInt(request.getParameter("raccno"));
		
		try {
			Model m = new Model();
			m.setAccno(accno);
			m.setBal(amt);
			m.setRaccno(raccno);
			boolean b=m.transfer();
			if(b==true) {
				System.out.println("Balance sucessfully transfer inside transfer servlet b is"+b);
				//store account number and send response to Home.html
				session.setAttribute("accno",m.getAccno());
				response.sendRedirect("/BankingApplication/TransferSucess.html");
			}
			else {
				response.sendRedirect("/BankingApplication/TransferFail.html");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
