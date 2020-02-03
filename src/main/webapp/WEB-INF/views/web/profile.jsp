<%@page import="com.jwatgroupb.service.CustomUserDetailsService"%>
<%@page import="javax.swing.text.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.jwatgroupb.util.SecurityUtils"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<div style="width: 40%; margin: auto">
		<ul class="nav nav-tabs">
			<li class="active"><a href="#home" data-toggle="tab">Profile</a></li>
			<li><a href="#UpdateInfomation" data-toggle="tab">Update
					Infomation</a></li>
			<li><a href="#newpassword" data-toggle="tab">Change Password</a></li>
		</ul>
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane active in" id="home" style="margin: auto">
				<form id="tab">

					<label style="padding: 10px 0 5px 0">Full Name</label><br> <input
						type="text"
						value="<%=SecurityUtils.replaceNull(SecurityUtils.getPrincipal().getName())%>"
						style="width: 100%; height: 40px" readonly><br> <label
						style="padding: 10px 0 5px 0">Email</label><br> <input
						type="text" value="<%=SecurityUtils.getPrincipal().getEmail()%>"
						style="width: 100%; height: 40px" readonly><br> <label
						style="padding: 10px 0 5px 0">Birthday</label><br> <input
						type="text"
						value="<fmt:formatDate type = "date" value = "<%=SecurityUtils.getPrincipal().getBirthday()%>"/>"
						style="width: 100%; height: 40px" readonly><br> <label
						style="padding: 10px 0 5px 0">Phone Number</label><br> <input
						type="text"
						value="<%=SecurityUtils.replaceNull(SecurityUtils.getPrincipal().getPhonenumber())%>"
						style="width: 100%; height: 40px" readonly><br> <label
						style="padding: 10px 0 5px 0">Address</label><br> <input
						type="text"
						value="<%=SecurityUtils.replaceNull(SecurityUtils.getPrincipal().getAddress())%>"
						style="width: 100%; height: 70px" readonly><br> <br>
				</form>
			</div>
			<div class="tab-pane fade" id="UpdateInfomation">
				<form:form id="tab2" action="addInfo" method="POST"
					modelAttribute="infoForm">

					<label style="padding: 10px 0 5px 0">Full Name</label>
					<br>
					<form:input path="name" type="text" placeholder="New name"
						style="width: 100%; height: 40px"></form:input>
					<br>
					<form:errors path="name" style="color: orangered"></form:errors>
					<br>

					<label style="padding: 10px 0 5px 0">Birthday</label>
					<br>
					<form:input path="birthday" type="date" placeholder="yyyy-mm-dd"
						style="width: 100%; height: 40px"></form:input>
					<br>
					<form:errors path="birthday" style="color: orangered"></form:errors>
					<br>

					<label style="padding: 10px 0 5px 0">Phone Number</label>
					<br>
					<form:input path="phonenumber" type="number"
						placeholder="New phone number" style="width: 100%; height: 40px"></form:input>
					<br>
					<form:errors path="phonenumber" style="color: orangered"></form:errors>
					<br>

					<label style="padding: 10px 0 5px 0">Address</label>
					<br>
					<form:input path="address" type="text" placeholder="New address"
						style="width: 100%; height: 70px"></form:input>
					<br>
					<form:errors path="address" style="color: orangered"></form:errors>
					<br>

					<div>
						<button class="btn btn-primary">Update</button>
					</div>
					<br>
				</form:form>
			</div>

			<div class="tab-pane fade" id="newpassword">
				<form id="tab3">

					<label style="padding: 10px 0 5px 0">New Password</label><br>
					<input type="password" style="width: 100%; height: 40px"><br>

					<div>
						<button class="btn btn-primary">Update</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>