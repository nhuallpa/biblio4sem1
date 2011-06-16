<html>
	<head>
		<meta name="layout" content="home" />
		<title>My Comment</title>
	</head>
	<body>
		
		<h3 class="long-title">My Comment</h3>
		
		<div id="items" class="shadowed">
			<div class="inner-boundary">
				<ul class="item-list">
					<g:each var="comment" in="${comments}">
						<li class="book">
							  <span class="item-title"><g:link controller="book" action="show" id="${comment.id}">${comment.description}</g:link></span>
<%--					          <h4>ISBN: ${reservation.book.ISBN}</h4>--%>
								  
<%--					          <span class="text">${reservation.book.state}</span>--%>
					          <p>${book?.subject}</p>
					    </li>
					</g:each>	
				</ul>
			</div>
		</div>
	</body>
</html>