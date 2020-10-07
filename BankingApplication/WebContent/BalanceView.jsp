<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Balance Page</title>
</head>
<body>
<h1>Dear Customer Your Current Account Balance</h1>
<%
       session=request.getSession();
      out.println(session.getAttribute("bal"));
%>
<h2>Logout</h2>
<a href="Login.html">|click here|</a>
</body>
</html>