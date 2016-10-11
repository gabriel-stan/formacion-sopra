<%@ include file="includes/header.jsp" %>
	
	<div id="contact-page" class="container">
    	<div class="bg">
    		<div class="row">  	
	    		<div class="col-sm-8 col-sm-offset-2">
	    			<div class="contact-form">
	    				<h2 class="title text-center">Add Category</h2>
	    				
	    				<c:if test="${ requestScope.page_message_success != null }">
		    				<div class="status alert alert-success">
								<c:out value="${ requestScope.page_message_success }"></c:out>
							</div>	    				
	    				</c:if>
	    				
	    				<c:if test="${ requestScope.page_message_error != null }">
		    				<div class="status alert alert-danger">
								<c:out value="${ requestScope.page_message_error }"></c:out>
							</div>	    				
	    				</c:if>
	    				
				    	<form id="main-contact-form" class="contact-form row" name="contact-form" method="post" action="categorias">

			                <input type="hidden" name="id" value="${ requestScope.categoria.id_categoria }">

				            <div class="form-group col-md-12">
				                <input type="text" name="name" class="form-control" required="required" placeholder="Name" value="${ requestScope.categoria.cat_nombre }">
				            </div>
				            <div class="form-group col-md-12">
				                <textarea name="description" id="message" required="required" class="form-control" rows="8" placeholder="Category Description">${ requestScope.categoria.cat_descripcion }</textarea>
				            </div>                        
				            <div class="form-group col-md-12">
				                <input type="submit" name="save" class="btn btn-primary pull-right" value="Save">
				            </div>
				        </form>
	    			</div>
	    		</div>			
	    	</div>  
    	</div>	
    </div><!--/#contact-page-->
	
<%@ include file="includes/footer.jsp" %>