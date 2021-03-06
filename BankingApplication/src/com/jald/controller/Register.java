package com.jald.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jald.model.Model;
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Collecting Data from Register.html page
		String name=request.getParameter("name");
		String custid=request.getParameter("custid");
		String saccno=request.getParameter("accno");
		int accno=Integer.parseInt(saccno);
		String pwd=request.getParameter("pwd");
		String sbal=request.getParameter("bal");
		int bal=Integer.parseInt(sbal);
		String email=request.getParameter("email");
		try {
			Model m = new Model();
			//setting up data in Model Class using m object and help of setters
			m.setName(name);
			m.setCustid(custid);
			m.setAccno(accno);
			m.setPwd(pwd);
			m.setBal(bal);
			m.setEmail(email);
            boolean b= m.register();
            //b returns true then it go SucessReg.html otherwise FailReg.html
            if(b==true) response.sendRedirect("/BankingApplication/SucessReg.html");
            else response.sendRedirect("/BankingApplication/FailReg.html");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
