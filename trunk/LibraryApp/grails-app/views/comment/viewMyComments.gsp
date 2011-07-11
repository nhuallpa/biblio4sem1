<html>
	<head>
		<meta name="layout" content="home" />
		<title>My Comments</title>
	</head>
	<body>
		
		<h3 class="long-title">My Comments</h3>
		
		<div id="items" class="shadowed">
			<div class="inner-boundary">
				<ul class="item-list">
					<g:each var="comment" in="${comments}">
						<li class="book">
						 						
<%--						 <g:link controller="book" action="show" id="${comment.id}">${comment.description}</g:link>--%>
						 <h4>${comment.description}</h4>
						 <p>Score: ${comment.score}</p>
<%--						 <p>${comment.date.toString()}</p>--%>
						 <g:link controller="book" action="viewDetails" params="[bookId:comment.book.id]">${comment.book.name}</g:link>
						 						 
<%--						<div class="list-operator-item">--%>
<%--				          	<span class="link-item"><g:link controller="comment" action="deleteComment" params="[commentId:comment.id]">Delete</g:link></span>--%>
<%--				          		|--%>
<%--				          		<span class="link-item"><g:link controller="comment" action="toComment" params="[bookId:book.id]">to Comment</g:link></span>--%>
<%--				          		|--%>
<%--				          		<span class="link-item"><a href="#">View</a></span>--%>
<%--				          </div>--%>
							
					    </li>
					</g:each>	
				</ul>
			</div>
		</div>
	</body>
</html>