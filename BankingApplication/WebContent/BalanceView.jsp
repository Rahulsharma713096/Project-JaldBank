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
      session.setAttribute("accno",session.getAttribute("accno"));
%>
<h3>Home Page <a href="Home.html">|click here|</a>  </h3>
<h2>Logout  <a href="Login.html">|click here|</a></h2>

</body>
</html>