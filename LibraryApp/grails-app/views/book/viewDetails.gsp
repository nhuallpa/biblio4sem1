<html>
	<head>
		<meta name="layout" content="home" />
		<title>Book: ${book?.name}</title>
	</head>
	<body>
		
		<h3 class="long-title">Book: ${book?.name}</h3>
		
		<div id="items" class="shadowed">
			<div class="inner-boundary">
				<p>ISBN: ${book?.ISBN }</p>
				<p>Subject: ${book?.subject }</p>
				<p>Rating: ${book?.rating }</p>
				<p>Total Votes: ${book?.totalVotes}</p>
				<span class="text">${book.state}</span>
				<ul class="item-list">
					<g:each var="comment" in="${book?.comments}">
						<li class="book">
							<p>${comment.description}</p>
							<p>User: ${comment.sourceUser}</p>
							<p>${comment.date}</p>
							<p>${comment.score}</p>
					    </li>
					</g:each>
				</ul>
			</div>
		</div>
	</body>
</html>