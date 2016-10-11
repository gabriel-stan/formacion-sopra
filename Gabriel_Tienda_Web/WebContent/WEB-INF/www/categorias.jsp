<%@ include file="includes/header.jsp" %>
	
		<section id="cart_items">
		<div class="container">
			<div class="breadcrumbs">
				<ol class="breadcrumb">
					<li><a href="home">Home</a></li>
					<li class="active">Categories</li>
					
					<c:if test="${ isAdmin }">
						<li class="pull-right"><a href="categorias?action=add"><i class="fa fa-plus"></i> Add Category</a></li>				  
					</c:if>
				</ol>
			</div>
	    	
	    	
	    				
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
			
			<c:if test="${ applicationScope.categorias != null && applicationScope.categorias.size() != 0 }">
				
				<div class="table-responsive cart_info">
					<table class="table table-condensed">
						<thead>
							<tr class="cart_menu">
								<td class="image"></td>
								<td class="description">Name</td>
								<td class="description">Description</td>
								<c:if test="${ isAdmin }">
									<td></td>
								</c:if>
							</tr>
						</thead>
						<tbody>
						
							<c:forEach items="${applicationScope.categorias}" var="cat">
						
								<tr>
									<td class="cart_product">
										<a href=""><img src="images/cart/one.png" alt=""></a>
									</td>
									<td class="cart_description">
										<h4><a href=""><c:out value="${ cat.cat_nombre }"></c:out></a></h4>
										<p>Cat ID: <c:out value="${ cat.id_categoria }"></c:out></p>
									</td>
									<td class="cart_description">
										<h4></h4>
										<p><c:out value="${ cat.cat_descripcion }"></c:out></p>
									</td>
									<c:if test="${ isAdmin }">
										<td class="cart_delete">
											<a class="cart_quantity_delete" href="categorias?action=edit&id=${ cat.id_categoria }"><i class="fa fa-pencil"></i></a>
											<a class="cart_quantity_delete open-dialog" href="#" data-toggle="modal" data-target="#myModal" data-id="${ cat.id_categoria }" data-nombre="${ cat.cat_nombre }"><i class="fa fa-times"></i></a>
										</td>									
									</c:if>
								</tr>
								
							</c:forEach>
								
						</tbody>
					</table>
				</div>
				
			</c:if>
			
			<c:if test="${ applicationScope.categorias == null || applicationScope.categorias.size() == 0 }">
				
				<div class="status alert alert-info">
					<strong>Info!</strong> No hay categorias.
				</div>	
			 
			</c:if>
			

		</div>
	</section> <!--/#cart_items-->
	
	
	  <div class="modal fade" id="myModal" role="dialog">
	    <div class="modal-dialog modal-sm">
	      <div class="modal-content">
	        <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	          <h4 class="modal-title">Delete Category</h4>
	        </div>
	        <div class="modal-body">
	          <p>¿Seguro que quieres borrar la categoria <b><span id="modal-cat-nombre"></span></b>?</p>
	          
	          
	          
	        </div>
	        <div class="modal-footer">
	        
	        	<form action="categorias" method="post">	        	
	        		<input type="hidden" name="id" id="modal-cat-id">
	        		<input type="hidden" name="action" value="delete">
	          		<input type="submit" name="delete" class="btn btn-danger" value="Delete">
	          		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        	</form>
	        
	        </div>
	      </div>
	    </div>
	  </div>
	  
	  <script type="text/javascript">
// 	  	function modal(e) {
// 		  	$('#myModal').on('shown.bs.modal', function() {
// 			    console.log("modal on");
// 			    console.log(e);
// 			    var invoker = $(e.relatedTarget);
// 			    console.log(invoker.get('id'));
// 			    var catID = $(this).data('id');
// 			    console.log(catID);
// 		        $(".modal-body #bookId").val( catID );
// 			});
// 		}

	  	function modal(e) {
	  		$(document).on("click", ".open-dialog", function () {
	  		     var id = $(this).data('id');
	  		     var nombre = $(this).data('nombre');
	  		     console.log(id);
	  		     console.log(nombre);
	  		     $(".modal-body #modal-cat-nombre").html( nombre );
	  		     //$(".modal-body #modal-cat-id").val( id );
	  		     document.getElementById("modal-cat-id").value = id;
	  		     // As pointed out in comments, 
	  		     // it is superfluous to have to manually call the modal.
	  		     // $('#addBookDialog').modal('show');
	  		});
		}
	  	
	  	window.addEventListener('load', modal, false )
	  </script>
	
<%@ include file="includes/footer.jsp" %>