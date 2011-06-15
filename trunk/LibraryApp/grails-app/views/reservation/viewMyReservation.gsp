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
					<li class="web-template">
						<h3 class="long-title">${reservation.book.title}</h3>
						<h2 ><strong>ISBN: </strong>${reservation.book.ISBN}</h2>
					</li>
					</g:each>	
				</ul>
			</div>
		</div>
	</body>
</html>