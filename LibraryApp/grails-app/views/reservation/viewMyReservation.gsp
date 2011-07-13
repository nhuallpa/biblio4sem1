<html>
	<head>
		<meta name="layout" content="home" />
		<title>My reservation</title>
	</head>
	<body>
		
		<h3 class="long-title">My reservation</h3>
		
		<g:if test="${flash.message}">
      		<div class="message">${flash.message}</div>
    	</g:if>
		
		<div id="items" class="shadowed">
			<div class="inner-boundary">
				<ul class="item-list">
					<g:each var="reservation" in="${reservations}">
						<li class="book">
							  <span class="item-title"><g:link controller="book" action="viewDetails" params="[bookId:reservation.book.id]">${reservation.book.name}</g:link></span>
					          <h4>ISBN: ${reservation.book.ISBN}</h4>
					          <span class="text">${reservation.book.state}</span>
					          <p>${book?.subject}</p>
					    <div class="list-operator-item">
				        	<span class="link-item"><g:link controller="reservation" action="cancelReserve" params="[bookId:reservation.book.id]">Cancel</g:link></span>
<%--				        	|--%>
<%--				        	<span class="link-item"><g:link controller="comment" action="toComment" params="[bookId:book.id]">to Comment</g:link></span>--%>
				        </div>
					    </li>

					</g:each>	
				</ul>
			</div>
		</div>
	</body>
</html>