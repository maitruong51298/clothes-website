<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<section id="slider">
		<!--slider-->
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<div id="slider-carousel" class="carousel slide"
						data-ride="carousel">
						<ol class="carousel-indicators">
							<li data-target="#slider-carousel" data-slide-to="0"
								class="active"></li>
							<li data-target="#slider-carousel" data-slide-to="1"></li>
							<li data-target="#slider-carousel" data-slide-to="2"></li>
						</ol>

						<div class="carousel-inner">
							<div class="item active">
								<div class="col-sm-6">
									<h1>
										<span>E</span>-SHOPPER
									</h1>
									<h2>Free E-Commerce Template</h2>
									<p>Lorem ipsum dolor sit amet, consectetur adipisicing
										elit, sed do eiusmod tempor incididunt ut labore et dolore
										magna aliqua.</p>
									<button type="button" class="btn btn-default get">Get
										it now</button>
								</div>

								<div class="col-sm-6">
									<img src='<c:url value="/template/web/images/home/girl1.jpg"/>'
										class="girl img-responsive" alt="" /> <img
										src='<c:url value="/template/web/images/home/pricing.png"/>'
										class="pricing" alt="" />
								</div>


							</div>
							<div class="item">
								<div class="col-sm-6">
									<h1>
										<span>E</span>-SHOPPER
									</h1>
									<h2>100% Responsive Design</h2>
									<p>Lorem ipsum dolor sit amet, consectetur adipisicing
										elit, sed do eiusmod tempor incididunt ut labore et dolore
										magna aliqua.</p>
									<button type="button" class="btn btn-default get">Get
										it now</button>
								</div>
								<div class="col-sm-6">
									<img src='<c:url value="/template/web/images/home/girl2.jpg"/>'
										class="girl img-responsive" alt="" /> <img
										src='<c:url value="/template/web/images/home/pricing.png"/>'
										class="pricing" alt="" />
								</div>
							</div>

							<div class="item">
								<div class="col-sm-6">
									<h1>
										<span>E</span>-SHOPPER
									</h1>
									<h2>Free Ecommerce Template</h2>
									<p>Lorem ipsum dolor sit amet, consectetur adipisicing
										elit, sed do eiusmod tempor incididunt ut labore et dolore
										magna aliqua.</p>
									<button type="button" class="btn btn-default get">Get
										it now</button>
								</div>
								<div class="col-sm-6">
									<img src='<c:url value="/template/web/images/home/girl3.jpg"/>'
										class="girl img-responsive" alt="" /> <img
										src='<c:url value="/template/web/images/home/pricing.png"/>'
										class="pricing" alt="" />
								</div>
							</div>

						</div>

						<a href="#slider-carousel" class="left control-carousel hidden-xs"
							data-slide="prev"> <i class="fa fa-angle-left"></i>
						</a> <a href="#slider-carousel"
							class="right control-carousel hidden-xs" data-slide="next"> <i
							class="fa fa-angle-right"></i>
						</a>
					</div>

				</div>
			</div>
		</div>
	</section>
	<!--/slider-->

	<section>
		<div class="container">
			<div class="row">
				<div class="col-sm-3">
					<div class="left-sidebar">
						<h2>Category</h2>
							<c:forEach items="${listCategory}" var="category">
								<div class="panel-group category-products" id="accordian">
									<!--category-products-->
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title">
												<a href="<c:url value="${category.categoryName}" />" >${category.categoryName}</a>
											</h4>
										</div>
		
									</div>
								</div>
							</c:forEach>
					</div>
				</div>

				<div class="col-sm-9 padding-right">
					<div class="features_items">
						<!--features_items-->
						<h2 class="title text-center">Features Items</h2>
						<c:forEach items="${listProductByCategory}" var="product">
							<div class="col-sm-4">
								<div class="product-image-wrapper">
									<div class="single-products">
										<div class="productinfo text-center">
										
												<a href='<c:url value="/product-details/${product.id}"/>'><img style="height: 230px" 
												src='<c:url value="${product.url1}"/>'
												alt="" /> 
											</a>
												
											<h2>$ ${product.price}</h2>
											<h2 style="">${product.name}</h2>
											<a href="/cart/add/1" class="btn btn-default add-to-cart"><i
												class="fa fa-shopping-cart"></i>Add to cart</a>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
			</div>
		</div>
		</div>
	</section>
</body>
</html>