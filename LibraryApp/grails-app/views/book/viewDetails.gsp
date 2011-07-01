<html>
	<head>
		<meta name="layout" content="home" />
		<title>Book View: ${book?.name}</title>
	</head>
	<body>
		
		<h3 class="long-title">${book?.name}</h3>
		
		<div id="items" class="shadowed">
			<div class="inner-boundary">
				<span class="text">ISBN: ${book?.ISBN }</span>
				|
				<span class="text">Subject: ${book?.subject }</span>
				|
				<span class="text">Rating: ${book?.rating }</span>
				|
				<span class="text">Total Votes: ${book?.totalVotes}</span>
				|
				<span class="text">State: ${book.state}</span>
				<ul class="item-list">
					<g:each var="comment" in="${book?.comments}">
						<li class="book">
							<h4>"${comment.description}"</h4>
							<span class="text"><g:link controller="user" action="viewProfile" params="[userId:comment.sourceUser.id]">User: ${comment.sourceUser.name} [${comment.sourceUser.rating}]</g:link></span>
							<p>${comment.date}</p>
							<p>Comment Score: ${comment.score}</p>
					    </li>
					</g:each>
				</ul>
			</div>
		</div>
	</body>
</html>