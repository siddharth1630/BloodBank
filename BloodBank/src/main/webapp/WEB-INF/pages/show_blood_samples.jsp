<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>

<!DOCTYPE html>
<html>
<head>
	<title>Blood samples</title>
</head>
<body>
<c:choose>
	<c:when test="${email ne null && !empty email && fn:contains(email, '.com')  }">
		<a href="back"><input type="button"  value="home"/></a>
		<a href="home"><input type="button"  value="Logout"/></a>
	</c:when>
	<c:otherwise>
		<a href="home"><input type="button"  value="home"/></a>
	</c:otherwise>
</c:choose>

		<frm:form modelAttribute="bReq" action="search">
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
					<td><a href="avalSample"><input type="button"  value="Get ALL Sample"/></a></td>
				<tr>
			</table>
		</frm:form>

<c:choose>
	<c:when test="${bloodInfo ne null && !empty bloodInfo }">
		<table border="1">
			<tr>
				<th>Blood Group Name</th>
				<th>Hospital Name</th>
				<th>Hospital Address</th>
				<th>Hospital city</th>
				<th>Hospital Contact</th>
				<th>Available Quantity</th>
				<c:choose>
					<c:when test="${email ne null && !empty email && fn:contains(email, '.com')  }">
						<th>Select Quantity</th>
					</c:when>
				</c:choose>
				<th>Action</th>
				
			</tr>
			<c:set var="count" value="0" scope="page" />
			<c:forEach   var="dto" items="${listHospInfo }">
				<tr>
					<td>${bloodInfo[count].bloodGrpName}</td>
					<td>${dto.hospName}</td>
					<td>${dto.hospAddrs}</td>
					<td>${dto.hospCity}</td>
					<td>${dto.hospMobNO}</td>
					<td>${bloodInfo[count].qty}</td>
					
					<c:choose>
						<c:when test="${email ne null && !empty email && fn:contains(email, '.com')  }">
							<frm:form	modelAttribute="bReq" action="avalSample">
								<frm:input type="hidden" path="hospRegId" value="${bloodInfo[count].hospRegId }"/>
								<frm:input type="hidden" path="bloodGrpName" value="${bloodInfo[count].bloodGrpName }"/>
								<frm:input type="hidden" path="recvEmail" value="${email }"/>
								<td><frm:input path="qty" /></td>
								<input type="hidden" name="avlqty" value="${bloodInfo[count].qty }"/>
								<td><input type="submit" value="Request Sample"/></td>
							</frm:form>
							
						</c:when>
						
						
						<c:otherwise>
							<td><a href="login"><input type="button"  value="Request Sample"/></a></td>
						</c:otherwise>
					</c:choose>
					<c:set var="count" value="${count + 1}" scope="page"/>
				</tr>
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
