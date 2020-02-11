<%@page import="com.jwatgroupb.service.CustomUserDetailsService"%>
<%@page import="javax.swing.text.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.jwatgroupb.util.SecurityUtils"%>
<%@include file="/common/taglib.jsp"%>

<title>Purchase History | E-Shopper</title>
<body>
	
	<table class="table table-striped" style="width: 60%; margin: auto">
		<thead>
			<tr>
				<th>#</th>
				<th>Receiver</th>
				<th>Address</th>
				<th>Order Date</th>
				<th>Delivery Date</th>
				<th>Status</th>
				<th>Net Amount</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listBill}" var="bill" varStatus="loop">
			<tr>
				<td>${loop.count}</td>
				<td>${bill.receiver}</td>
				<td>${bill.address}</td>
				<td>${bill.createdDate}</td>
				<td><fmt:formatDate type="date" value = "${bill.deliveryDate}"/></td>
				<c:set var = "status" value = "${bill.status}"/>
				<c:if test="${status eq 1}">
							<td>Order Success</td>
				</c:if>
				<c:if test="${status eq 0}">
							<td>Order Rejected</td>
				</c:if>
				<td>${bill.totalMoney}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table><br>
</body>