<html>
	<head>
		<meta name="layout" content="home" />
		<title>My reservation</title>
	</head>
	<body>
		
		<h3 class="long-title">My reservation</h3>
		
		<div id="items" class="shadowed">
			<div class="inner-boundary">
				<ul class="item-list">
					<g:each var="reservation" in="${reservations}">
						<li class="book">
							  <span class="item-title"><g:link controller="book" action="show" id="${reservation.book.id}">${reservation.book.name}</g:link></span>
					          <h4>ISBN: ${reservation.book.ISBN}</h4>
					          <span class="text">${reservation.book.state}</span>
					          <p>${book?.subject}</p>
					    </li>
					</g:each>	
				</ul>
			</div>
		</div>
	</body>
</html>