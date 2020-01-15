<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<section id="form">
		<!--form-->
		<div class="container">
			<div class="row">
				<div class="col-sm-4 col-sm-offset-1">
					<div class="login-form">
						<!--login form-->
						<h2>Login to your account</h2>
						
						<c:if test="${param.incorrectAccount != null}">
							<div class="alert alert-danger">Username or password
								incorrect</div>
						</c:if>
						<c:if test="${param.accessDenied != null}">
							<div class="alert alert-danger">you Not authorize</div>
						</c:if>
						
						<form action="j_spring_security_check" method="POST">
							<input type="text" placeholder="Name" name="j_username" />
							<input type="password" placeholder="Password" name="j_password"  />
							<span> <input type="checkbox" class="checkbox">
								Keep me signed in
							</span>
							<button type="submit" class="btn btn-default">Login</button>
						</form>
					</div>
					<!--/login form-->
				</div>
				<div class="col-sm-1">
					<h2 class="or">OR</h2>
				</div>
				<div class="col-sm-4">
					<div class="signup-form">
						<!--sign up form-->
						<h2>New User Signup!</h2>
						<form action="#">
							<input type="text" placeholder="Name" /> <input type="email"
								placeholder="Email Address" /> <input type="password"
								placeholder="Password" />
							<button type="submit" class="btn btn-default">Signup</button>
						</form>
					</div>
					<!--/sign up form-->
				</div>
			</div>
		</div>
	</section>
	<!--/form-->
</body>
</html>