<html>
	<head>
		<meta name="layout" content="home" />
		<title>My Comments</title>
	</head>
	<body>
		
		<h3 class="long-title">My Comments</h3>
		
		<g:if test="${flash.message}">
      		<div class="message">${flash.message}</div>
    	</g:if>
		
		<div id="items" class="shadowed">
			<div class="inner-boundary">
				<ul class="item-list">
					<g:each var="comment" in="${comments}">
						
							 <g:if test="${comment}">
								 	<li class="book">
									 <h4>${comment.description}</h4>
									 <p>Score: ${comment.score}</p>
									 <g:link controller="book" action="viewDetails" params="[bookId:comment.book.id]">${comment.book.name}</g:link>
							    	 <div class="list-operator-item">
						        	 <span class="link-item"><g:link controller="comment" action="deleteComment" params="[commentId:comment.id,bookId:comment.book.id]">Delete</g:link></span>
		<%--				        	|--%>
		<%--				        	<span class="link-item"><g:link controller="comment" action="toComment" params="[bookId:book.id]">to Comment</g:link></span>--%>
						        	 </div>
						        	 </li>		
							 </g:if>
					 
					    
					</g:each>	
				</ul>
			</div>
		</div>
	</body>
</html>