<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>

<!DOCTYPE html>
<html>
<head>
	<title>Blood Request</title>
</head>
<body>
<a href="hback"><input type="button"  value="Back"/></a>
<a href="home"><input type="button"  value="Logout"/></a>
<frm:form modelAttribute="brReq" >
	<table>
		<tr>
			<td>Search By::</td>
			<td>Blood Group</td>
			<td>	
				<frm:select path="bloodGrpName">
					<frm:options items="${ bloodGrpNames}"/>
				</frm:select>
			</td>
			<td><input type="submit" value="Search"/></td>
			<td><a href="viewReq"><input type="button"  value="Get All Request"/></a></td>
		<tr>
	</table>
</frm:form>
<c:choose>
	<c:when test="${bloodReqInfo ne null && !empty bloodReqInfo }">
		<table border="1">
			<tr>
				<th>Registered Blood Group Name</th>
				<th>Demanded Blood Group Name</th>
				<th>Quantity</th>
				<th>Receiver Name</th>
				<th>Receiver City</th>
				<th>Receiver Email</th>
				<th>Receiver Mobile no.</th>
			</tr>
			<c:set var="count" value="0" scope="page" />
			<c:forEach var="dto" items="${recvDetail }">
				<tr>
					<td>${dto.bloodGrpName}</td>
					<td>${bloodReqInfo[count].bloodGrpName}</td>
					<td>${bloodReqInfo[count].qty}</td>
					<td>${dto.recvName}</td>
					<td>${dto.recvCity}</td>
					<td>${bloodReqInfo[count].recvEmail}</td>
					<td>${dto.recvMobNO}</td>		
				</tr>
				<c:set var="count" value="${count + 1}" scope="page"/>
			</c:forEach>
		</table>
	</c:when>
	<c:otherwise>
		<h1>Record not found</h1>
	</c:otherwise>
</c:choose>

<h1><a href="javaScript:doPrint()">Print</a></h1>
<marquee direction="right"><h1>${registerMsg }</h1></marquee>
<script lang="javaScript">
	function doPrint(){
		frames.focus();
		frames.print();
	}
</script>
</body>
</html>
