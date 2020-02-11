<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User List</title>
</head>



<body>

	<h2 align="center" style="color: green;" class="alert alert-info">User List</h2>
	<br>
	<c:if test="${listUser != null}">

		<form method="POST" action="search" >

        User Name or Email  <input  type="text" name="keyword" id="keyword" >
  					<input type ="submit" value="Search" >
    </form>
    
    	<form method="GET" action="adduser" >

        
  					<input type ="submit" value="Add user">
    </form>

		<table border="1" cellpadding="5">
		

			<tr align="center" style="background-color: silver;">
				<th align="center">ID</th>
				<th align="center">User Name</th>
				<th align="center">Email</th>
				<th align="center">Password</th>
				<th align="center">Active</th>
				<th align="center">Role User</th>

			</tr>
			<c:forEach items="${listUser}" var="user" >
			
				<tr >
						
					<td align="center"><c:out value="${user.id}"></c:out></td>
					<td align="center"><c:out value="${user.userName}"></c:out></td>
					<td align="center"><c:out value="${user.email}"></c:out></td>
					<td align="center"><c:out value="${user.password}"></c:out></td>
					<td align="center"><c:out  value="${user.active}"></c:out></td>
					<td align="center"><c:out value="${user.roleUserEntity.roleName}"></c:out></td>
				
					<td>
					<form action = "edit/${user.userName}">
					<input  type="submit" value="Edit"></form> 
					</td> 
					<td>
					<form action = "delete/${user.id}" name = "${user.id}" >
					<input type="submit" value="Delete"></form> 
					</td> 

			</c:forEach>
		</table>
	</c:if>

</body>
</html>