<%@ include file="includes/header.jsp" %>
	
	<div id="contact-page" class="container">
    	<div class="bg">
    		<div class="row">  	
	    		<div class="col-sm-8 col-sm-offset-2">
	    			<div class="contact-form">
	    				<h2 class="title text-center">Add Category</h2>
	    				
	    				<%@ include file="includes/messages.jsp"%>
	    				
				    	<form:form id="main-contact-form" class="contact-form row" name="contact-form" method="post" modelAttribute="categoriaBean">

			                <form:input type="hidden" name="id" path="id_categoria" />

				            <div class="form-group col-md-12">
				                <form:input type="text" name="name" class="form-control" required="required" placeholder="Name" path="cat_nombre" />
				            </div>
				            <div class="form-group col-md-12">
				                <form:textarea name="description" id="message" class="form-control" rows="8" placeholder="Category Description" path="cat_descripcion" />
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