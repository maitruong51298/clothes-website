<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Result</title>
</head>
<body>
<div align="left">
    <h2>Search Result</h2>
     <td>
					<form action = "listUser">
					<input  type="submit" value="List Users"></form> 
					</td> 
    <table border="1" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>User Name</th>
            <th>Password</th>
            <th>E-mail</th>
            <th>Active</th>
        </tr>
        <c:forEach items="${result}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.userName}</td>
            <td>${user.password}</td>
            <td>${user.email}</td>
            <td>${user.active}</td>
              <td>
					<form action = "edit/${user.userName}">
					<input  type="submit" value="Edit"></form> 
					</td> 
					<td>
					<form action = "delete/${user.id}" name = "${user.id}" >
					<input type="submit" value="Delete"></form> 
					</td> 
        </tr>
      
        </c:forEach>
    </table>
</div>   
</body>
</html>