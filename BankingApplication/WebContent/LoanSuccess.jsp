<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LoanSuccess Page</title>
</head>
<body bgcolor="silver">

<%  
   session=request.getSession();
    out.println("Dear,"+session.getAttribute("name")+" thank you for showing your interest on Loans from JALDBank .");
    out.println("<br> Our excutive will contact you soon on your email address mention below : <br>");
    out.println(session.getAttribute("email"));
    
%>

<h5>Go  to Home Page <a href="Home.html"><button>click here</button></a></h5>
<form action="Logout.html">
<input type="submit" value="Logout"/>
</form>
</body>
</html>