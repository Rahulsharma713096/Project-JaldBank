<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GetStatementSuccess Page</title>
</head>
<body>

<%
       session=request.getSession();
           out.println("Account No:"+session.getAttribute("accno")+"<br><br>");
           out.println("<h3>Transection Details are ...</h3>");
           out.println(session.getAttribute("sdata"));
           session.setAttribute("accno",session.getAttribute("accno"));

%>
<h3>Home Page <a href="Home.html">|click here|</a>  </h3>
<h2>Logout  <a href="Login.html">|click here|</a></h2>
</body>
</html>