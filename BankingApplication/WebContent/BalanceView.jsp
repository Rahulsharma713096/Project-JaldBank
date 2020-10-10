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
<h3><a href="Home.html"><button>Home Page </button></a>  </h3>
<h3><a href="Login.html"><button>Logout </button></a></h3>

</body>
</html>