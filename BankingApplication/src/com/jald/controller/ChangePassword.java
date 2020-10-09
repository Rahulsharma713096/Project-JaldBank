package com.jald.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jald.model.Model;

 @WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pwd = request.getParameter("npwd");
		HttpSession session = request.getSession();
		int accno = (int) session.getAttribute("accno");
		try {
			Model m = new Model();
			m.setAccno(accno);
			m.setPwd(pwd);
			boolean b=m.changePassword();
			if(b==true) {
				System.out.println("Change Password SucessFul");
				response.sendRedirect("/BankingApplication/ChangePasswordSucess.html");
			}else {
				System.out.println("Password coudn't be change");
				response.sendRedirect("/BankingApplication/ChangepwdFail.html");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
