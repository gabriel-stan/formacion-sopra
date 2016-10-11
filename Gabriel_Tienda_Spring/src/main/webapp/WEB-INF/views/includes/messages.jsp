
	    				
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