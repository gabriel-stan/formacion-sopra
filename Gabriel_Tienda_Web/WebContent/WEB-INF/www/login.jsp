<%@ include file="includes/header.jsp" %>
	
	<section id="form"><!--form-->
		<div class="container">
			<div class="row">				
				<div class="col-sm-9 col-sm-offset-1">
					
					<c:if test = "${requestScope.page_message_success != null}">
						<div class="alert alert-success">
						  <strong>Success!</strong> <c:out value="${requestScope.page_message_success}"/>
						</div>
					</c:if>
					
					<c:if test = "${requestScope.page_message_error != null}">
						<div class="alert alert-danger">
						  <strong>Error!</strong> <c:out value="${requestScope.page_message_error}"/>
						</div>
					</c:if>
					
				</div>
			</div>
		
			<div class="row">
				<div class="col-sm-4 col-sm-offset-1">
					<div class="login-form"><!--login form-->
						<h2>Login to your account</h2>
						<form action="login" method="post">
							<input type="email" name="email" placeholder="Email Address" />
							<input type="password" name="password" placeholder="Password" />
							<span>
								<input type="checkbox" class="checkbox"> 
								Keep me signed in
							</span>
							<button type="submit" class="btn btn-default">Login</button>
						</form>
					</div><!--/login form-->
				</div>
				<div class="col-sm-1">
					<h2 class="or">OR</h2>
				</div>
				<div class="col-sm-4">
					<div class="signup-form"><!--sign up form-->
						<h2>New User Signup!</h2>
						<form action="register" method="post">
							<input type="text" name="nombre" placeholder="Name"/>
							<input type="email" name="email" placeholder="Email Address"/>
							<input type="text" name="dni" placeholder="DNI"/>
							<input type="password" name="password" placeholder="Password"/>
							<button type="submit" class="btn btn-default">Signup</button>
						</form>
					</div><!--/sign up form-->
				</div>
			</div>
		</div>
	</section><!--/form-->
	
<%@ include file="includes/footer.jsp" %>