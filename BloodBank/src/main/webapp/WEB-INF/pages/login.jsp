<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hospital Registration</title>
	<script  language="JavaScript" src="js/validation.js" ></script>
</head>

<body>
<h1> Login page</h1>
<br>
<a href="home"><input type="button"  value="Reciver Registration"/></a>
<a href="hospReg"><input type="button"  value="Hospital Registration"/></a>
<br><br>
	<frm:form	modelAttribute="login" onsubmit="return validate(this)">
		<table>			
			<tr>
				<td>Email/Hosp. Reg ID</td>
				<td><frm:input  path="email"/><frm:errors path="email"/><span id="emaiId"></span></td>
			<tr>
			<tr>
				<td>Password</td>
				<td><frm:input type="password" path="pass"/><frm:errors path="pass"/><span id="passId"></span></td>
			<tr>
			
			<tr>
				<td colspan="2"><input type="submit" value="Login"/></td>
			</tr>
		</table>
		<frm:hidden path="jsFlag"/>
	</frm:form>
<marquee direction="right"><h1>${registerMsg }</h1></marquee>
</body>
</html>