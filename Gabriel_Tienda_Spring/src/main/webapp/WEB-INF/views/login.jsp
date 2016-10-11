<%@ include file="includes/header.jsp" %>
	
<!-- 	<section id="form">form -->
	<section id="form"><!--form-->
		<div class="container">
			<div class="row">				
				<div class="col-sm-9 col-sm-offset-1">
					
					<%@ include file="includes/messages.jsp"%>
					
				</div>
			</div>
		
			<div class="row">
				<div class="col-sm-4 col-sm-offset-1">
					<div class="login-form"><!--login form-->
						<h2>Login to your account</h2>
						<form:form action="login" method="post" modelAttribute="loginBean">
							<form:input type="email" name="email" placeholder="Email Address" path="user_email" />
							<form:input type="password" name="password" placeholder="Password" path="user_pass" />
							<span>
								<input type="checkbox" class="checkbox"> 
								Keep me signed in
							</span>
							<button type="submit" class="btn btn-default">Login</button>
						</form:form>
					</div><!--/login form-->
				</div>
				<div class="col-sm-1">
					<h2 class="or">OR</h2>
				</div>
				<div class="col-sm-4">
					<div class="signup-form"><!--sign up form-->
						<h2>New User Signup!</h2>
						<form:form action="register" method="post" modelAttribute="loginBean">
							<form:input type="text" name="nombre" placeholder="Name" path="user_nombre" />
							<form:input type="email" name="email" placeholder="Email Address" path="user_email" />
							<form:input type="text" name="dni" placeholder="DNI" path="user_dni" />
							<form:input type="password" name="password" placeholder="Password" path="user_pass" />
							<button type="submit" class="btn btn-default">Signup</button>
						</form:form>
					</div><!--/sign up form-->
				</div>
			</div>
		</div>
	</section><!--/form-->
	
<%@ include file="includes/footer.jsp" %>