<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>

	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-50">
				<form class="login100-form validate-form">
					<span class="login100-form-title p-b-33"> Account Login </span>

					<div class="wrap-input100 validate-input"
						data-validate="Username is required">
						<input class="input100" type="text" name="username"
							placeholder="Username"> <span class="focus-input100-1"></span>
						<span class="focus-input100-2"></span>
					</div>

					<div class="wrap-input100 rs1 validate-input"
						data-validate="Password is required">
						<input class="input100" type="password" name="pass"
							placeholder="Password"> <span class="focus-input100-1"></span>
						<span class="focus-input100-2"></span>
					</div>

					<div class="container-login100-form-btn m-t-20">
						<button class="login100-form-btn" type="submit">Sign in</button>
					</div>

				</form>
			</div>
		</div>
	</div>


</body>
</html>