<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hospital Home page</title>
</head>
<body>
	<a href="viewReq"><input type="button"  value="View All Requests"/></a>
	<a href="home"><input type="button"  value="LogOut"/></a>
	<marquee direction="right"><h1>${registerMsg }</h1></marquee>
	<br><br>
	<a href="addBlood"><input type="button"  value="Add Blood"/></a>
</body>
</html>