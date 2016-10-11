<%@ include file="includes/header.jsp" %>
	
	<div id="contact-page" class="container">
    	<div class="bg">
    		<div class="row">  	
	    		<div class="col-sm-8 col-sm-offset-2">
	    			<div class="contact-form">
	    				<h2 class="title text-center">Add User</h2>
	    				
	    				<%@ include file="includes/messages.jsp"%>
	    				
				    	<form:form id="main-contact-form" class="contact-form row" name="contact-form" method="post" modelAttribute="usuarioBean">

			                <form:input type="hidden" name="id" path="id_usuario" />

				            <div class="form-group col-md-12">
				                <form:input type="text" name="name" class="form-control" required="required" placeholder="Name" path="user_nombre" />
				            </div>
				            <div class="form-group col-md-12">
				                <form:input type="email" name="email" class="form-control" required="required" placeholder="Email" path="user_email" />
				            </div>  
				            <div class="form-group col-md-12">
				                <form:input type="password" name="password" class="form-control" required="required" placeholder="Password" path="user_pass" />
				            </div>   
				            <div class="form-group col-md-12">
				                <form:input type="text" name="dni" class="form-control" required="required" placeholder="DNI" path="user_dni" />
				            </div>		            
				            <div class="form-group col-md-12">
					            <form:select name='tipo' class="form-control" required="required" path="user_tipo">  
					            	<form:option value="0"/>
					            	<form:option value="1"/>
					            	<form:option value="2"/>
					            	<form:option value="3"/>
					            	<form:option value="4"/>
					            	<form:option value="9"/>
								</form:select>   
							</div>
				            <div class="form-group col-md-12">
				                <input type="submit" name="save" class="btn btn-primary pull-right" value="Save" />
				            </div>
						</form:form>
	    			</div>
	    		</div>			
	    	</div>  
    	</div>	
    </div><!--/#contact-page-->
	
<%@ include file="includes/footer.jsp" %>