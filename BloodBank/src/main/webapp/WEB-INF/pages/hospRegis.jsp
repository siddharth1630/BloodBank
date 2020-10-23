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
<h1> Hospital Registration page</h1>
<br>
<a href="home"><input type="button"  value="Reciver Registration"/></a>
<a href="login"><input type="button"  value="Login"/></a>
<br><br>
	<frm:form	modelAttribute="hospReg" onsubmit="return validate(this)">
		<table>
			<tr>
				<td> Name</td>
				<td><frm:input path="hospName"/><frm:errors path="hospName"/><span id="hospNameId"></span></td>
			<tr>
			<tr>
				<td>Address</td>
				<td><frm:input path="hospAddrs"/><frm:errors path="hospAddrs"/><span id="hospAddrsId"></span></td>
			<tr>
			
			<tr>
				<td>City</td>
				<td>	
					<frm:select path="hospCity">
						<frm:options items="${ recvCities}"/>
					</frm:select>
				</td>
			<tr>
			<tr>
				<td>Mobile number</td>
				<td><frm:input path="hospMobNO"/><frm:errors path="hospMobNO"/><span id="hospMobNOId"></span></td>
			<tr>
			
			<tr>
				<td>Hosp. Registration ID</td>
				<td><frm:input  path="hospRegId"/><frm:errors path="hospRegId"/><span id="hospRegId"></span></td>
			<tr>
			<tr>
				<td>Password</td>
				<td><frm:input type="password" path="hospPass"/><frm:errors path="hospPass"/><span id="hospPassId"></span></td>
			<tr>
			<tr>
				<td>Confirm Password</td>
				<td><frm:input type="password" path="hospCPass"/><frm:errors path="hospCPass"/><span id="hospCPassId"></span></td>
			<tr>
			<tr>
				<td colspan="2"><input type="submit" value="register"/></td>
			</tr>
		</table>
		<frm:hidden path="jsFlag"/>
	</frm:form>
<marquee direction="right"><h1>${registerMsg }</h1></marquee>
</body>
</html>