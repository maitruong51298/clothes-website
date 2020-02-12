<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<section id="cart_items">
		<div class="container">
			<div class="breadcrumbs">
				<ol class="breadcrumb">
					<li><a href="#">Home</a></li>
					<li class="active">Shopping Cart</li>
				</ol>
			</div>
			<div class="table-responsive cart_info">
				<table class="table table-condensed">
					<thead>
						<tr class="cart_menu">
							<td class="image">Item</td>
							<td class="description"></td>
							<td class="price">Price</td>
							<td class="quantity">Quantity</td>
							<td class="total">Total</td>
							<td></td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="6" align="center"><c:if
									test="${cart.getListCartItem().isEmpty()}">
									You have no items in your shopping cart.<a
										href='<c:url value="/HomePage"/>'
										style="text-decoration: underline;"> Back to shopping.</a>
								</c:if></td>
						</tr>

						<c:set var="total" value="0" />
						<c:forEach items="${cart.getListCartItem()}" var="cartitem">
							<c:set var="price"
								value="${cartitem.getProductEntity().getPrice()-cartitem.getProductEntity().getPrice()*cartitem.getProductEntity().getPromotion()}" />

							<tr>
								<td class="cart_product"><a href=""><img
										src="images/cart/one.png" alt=""></a></td>
								<td class="cart_description">
									<h4>
										<a href="">${cartitem.getProductEntity().getName()}</a>
									</h4>
									<p>Web ID: 1089772</p>
								</td>
								<td class="cart_price">
									<p>$ ${price}</p>
								</td>
								<td class="cart_price">
									<p>${cartitem.getQuanity()}</p>
								</td>
								<td class="cart_quantity">
									<div class="cart_quantity_button">
										<a class="cart_quantity_up" href=""> + </a> <input
											class="cart_quantity_input" type="text" name="quantity"
											value="${cartitem.getQuanity()}" autocomplete="off" size="2">
										<a class="cart_quantity_down" href=""> - </a>
									</div>
								</td>
								<c:set var="pricetotal" value="${price*cartitem.getQuanity()}"></c:set>
								<td class="cart_total">
									<p class="cart_total_price">$
										${pricetotal}</p>
								</td>
								<td class="cart_delete"><a class="cart_quantity_delete"
									href=""><i class="fa fa-times"></i></a></td>
							</tr>
							<c:set var="total" value="${total+ pricetotal}" />
						</c:forEach>

					</tbody>
				</table>
			</div>
		</div>
	</section>
	<!--/#cart_items-->
	<section id="do_action">
		<div class="container">

			<div class="row">
				<div class="col-sm-6"></div>
				<div class="col-sm-6">
					<div class="total_area">
						<ul>
							<li>Cart Sub Total <span>$${total}</span></li>
							<li>Shipping Cost <span>Free</span></li>
							<li>Total <span>$${total}</span></li>
						</ul>
						<a class="btn btn-default btn-lg check_out pull-right"
							href='<c:url value="/checkout"/>'>Check Out</a>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--/#do_action-->


</body>
</html>