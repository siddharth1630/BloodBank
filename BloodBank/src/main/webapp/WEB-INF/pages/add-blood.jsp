<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Blood Details</title>
	
</head>

<body>
<h1> Add Blood Details</h1>
<br>
<a href="home"><input type="button"  value="Logout"/></a>
<br><br>

	<frm:form	modelAttribute="avlBlood" >
		<table>
			<tr>
				<td>Blood Group</td>
				<td>	
					<frm:select path="bloodGrpName">
						<frm:options items="${ bloodGrpNames}"/>
					</frm:select>
				</td>
			<tr>
			<tr>
				<td>QTY</td>
				<td>	
					<frm:select path="qty">
						<frm:options items="${ qty}"/>
					</frm:select>
				</td>
			<tr>
			
				
				<td><frm:input type="hidden" path="hospRegId" value="${email }"/>
			
			
			<tr>
				<td colspan="2"><input type="submit" value="register"/></td>
			</tr>
		</table>
		
	</frm:form>
<marquee direction="right"><h1>${registerMsg }</h1></marquee>
</body>
</html>