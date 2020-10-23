<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reciver Home page</title>
</head>
<body>
	<a href="home"><input type="button"  value="LogOut"/></a>
	<a href="avalSample"><input type="button"  value="Available sample"/></a>
	<marquee direction="right"><h1>${registerMsg }</h1></marquee>
	<h1>${email }</h1>
</body>
</html>