<html>
	<head>
		<meta name="layout" content="home" />
		<title>My reservation</title>
	</head>
	<body>
		my reservation
		<div id="list">
			<g:each var="reservation" in="${reservations}">
				Book name : ${reservation.book.title}<br/>
				ISBN : ${reservation.book.ISBN}<br/>
			</g:each>
		</div>
	</body>
</html>