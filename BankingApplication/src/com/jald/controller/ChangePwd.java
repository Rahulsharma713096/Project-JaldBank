package com.jald.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;


public class ChangePwd implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String npwd = request.getParameter("npwd");
		String cpwd = request.getParameter("cpwd");
		if(npwd.equals(cpwd)) {
			chain.doFilter(request, response);
		}
		else {
			System.out.println("Password coudn't be match");
			((HttpServletResponse)response).sendRedirect("/BankingApplication/ChangepwdFail.html");
		}
	}

	
}
