<%@ include file="includes/header.jsp" %>
	
		<section id="cart_items">
		<div class="container">
			<div class="breadcrumbs">
				<ol class="breadcrumb">
					<li><a href="home">Home</a></li>
					<li class="active">Users</li>
					
					<c:if test="${ isAdmin }">
						<li class="pull-right"><a href="users/add"><i class="fa fa-plus"></i> Add User</a></li>				  
					</c:if>
				</ol>
			</div>
	    	
			<%@ include file="includes/messages.jsp"%>
			
			<c:if test="${ sessionScope.usuarios != null && sessionScope.usuarios.size() != 0 }">
				
				<div class="table-responsive cart_info">
					<table class="table table-condensed">
						<thead>
							<tr class="cart_menu">
								<td class="image"></td>
								<td class="description">Name</td>
								<td class="description">Email</td>
								<td class="description">DNI</td>
								<td class="description">Type</td>
								<c:if test="${ isAdmin }">
									<td></td>
								</c:if>
							</tr>
						</thead>
						<tbody>
						
							<c:forEach items="${sessionScope.usuarios}" var="user">
						
								<tr>
									<td class="cart_product">
										<a href=""><img src="images/cart/one.png" alt=""></a>
									</td>
									<td class="cart_description">
										<h4><a href=""><c:out value="${ user.user_nombre }"></c:out></a></h4>
										<p>User ID: <c:out value="${ user.id_usuario }"></c:out></p>
									</td>
									<td class="cart_description">
										<h4></h4>
										<p><c:out value="${ user.user_email }"></c:out></p>
									</td>
									<td class="cart_description">
										<h4></h4>
										<p><c:out value="${ user.user_dni }"></c:out></p>
									</td>
									<td class="cart_description">
										<h4></h4>
										<p><c:out value="${ user.user_tipo }"></c:out></p>
									</td>
									<c:if test="${ isAdmin }">
										<td class="cart_delete">
											<a class="cart_quantity_delete" href="users/edit/${ user.id_usuario }"><i class="fa fa-pencil"></i></a>
											<a class="cart_quantity_delete open-dialog" href="#" data-toggle="modal" data-target="#myModal" data-id="${ user.id_usuario }" data-nombre="${ user.user_nombre }"><i class="fa fa-times"></i></a>
										</td>									
									</c:if>
								</tr>
								
							</c:forEach>
								
						</tbody>
					</table>
				</div>
				
			</c:if>
			
			<c:if test="${ sessionScope.usuarios == null || sessionScope.usuarios.size() == 0 }">
				
				<div class="status alert alert-info">
					<strong>Info!</strong> No hay usuarios.
				</div>	
			 
			</c:if>
			

		</div>
	</section> <!--/#cart_items-->
	
	
	  <div class="modal fade" id="myModal" role="dialog">
	    <div class="modal-dialog modal-sm">
	      <div class="modal-content">
	        <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	          <h4 class="modal-title">Delete User</h4>
	        </div>
	        <div class="modal-body">
	          <p>¿Seguro que quieres borrar el usuario <b><span id="modal-user-nombre"></span></b>?</p>
	          
	          
	          
	        </div>
	        <div class="modal-footer">
	        
	        	<form action="users/delete" method="post">	        	
	        		<input type="hidden" name="id" id="modal-user-id">
	        		<input type="hidden" name="action" value="delete">
	          		<input type="submit" name="delete" class="btn btn-danger" value="Delete">
	          		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        	</form>
	        
	        </div>
	      </div>
	    </div>
	  </div>
	  
	  <script type="text/javascript">
	  	function modal(e) {
	  		$(document).on("click", ".open-dialog", function () {
	  		     var id = $(this).data('id');
	  		     var nombre = $(this).data('nombre');
	  		     console.log(id);
	  		     console.log(nombre);
	  		     $(".modal-body #modal-user-nombre").html( nombre );
	  		     document.getElementById("modal-user-id").value = id;
	  		});
		}
	  	
	  	window.addEventListener('load', modal, false )
	  </script>
	
<%@ include file="includes/footer.jsp" %>