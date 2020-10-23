<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register employee</title>
<script  src='<c:url value="/resources/js/validation.js" />' ></script>
</head>

<body>
<h1> Employee Registration page</h1>
<br>
<a href="hospReg"><input type="button"  value="Hospital Registration"/></a>
<a href="login"><input type="button"  value="Login"/></a>
<a href="avalSample"><input type="button"  value="Available Blood Samples"/></a>
<br><br>
	<frm:form	modelAttribute="recvReg" onsubmit="return validate(this)">
		<table>
			<tr>
				<td> Name</td>
				<td><frm:input path="recvName"/><frm:errors path="recvName"/><span id="recvNameId"></span></td>
			<tr>
			<tr>
				<td>Address</td>
				<td><frm:input path="recvAddrs"/><frm:errors path="recvAddrs"/><span id="recvAddrsId"></span></td>
			<tr>
			
			<tr>
				<td>City</td>
				<td>	
					<frm:select path="recvCity">
						<frm:options items="${ recvCities}"/>
					</frm:select>
				</td>
			<tr>
			<tr>
				<td>Mobile number</td>
				<td><frm:input path="recvMobNO"/><frm:errors path="recvMobNO"/><span id="recvMobNOId"></span></td>
			<tr>
			<tr>
				<td>Blood Group</td>
				<td>	
					<frm:select path="bloodGrpName">
						<frm:options items="${ bloodGrpNames}"/>
					</frm:select>
				</td>
			<tr>
			<tr>
				<td>Email</td>
				<td><frm:input type="email" path="recvEmail"/><frm:errors path="recvEmail"/><span id="recvEmailId"></span></td>
			<tr>
			<tr>
				<td>Password</td>
				<td><frm:input type="password" path="recvPass"/><frm:errors path="recvPass"/><span id="recvPassId"></span></td>
			<tr>
			<tr>
				<td>Confirm Password</td>
				<td><frm:input type="password" path="recvCPass"/><frm:errors path="recvCPass"/><span id="recvCPassId"></span></td>
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