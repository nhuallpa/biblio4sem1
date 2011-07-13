<html>
	<head>
		<meta name="layout" content="home" />
		<title>Book View: ${book?.name}</title>
	</head>
	<body>
		
		<h3 class="long-title">${book?.name}</h3>
		
		<div id="items" class="shadowed">
			<div class="inner-boundary">
				<div class="book-description">
					<img  src="<g:createLinkTo dir="images/Book/${book.name}" file="cover.jpg" />"/>
					<div class="details-more-info">
					<span class="text">ISBN: ${book?.ISBN}</span><br/>
					<span class="text">Subject: ${book?.subject }</span><br/>
					<span class="text">Rating: ${book?.rating }</span><br/>
					<span class="text">Total Votes: ${book?.totalVotes}</span><br/>
					<span class="text">State: ${book.state}</span><br/>
					</div>
					<div class="list-operator-item">
						<span class="link-item"><g:link controller="reservation" action="toReserve" params="[bookId:book.id]">to Reserve</g:link></span>
						|
					  	<span class="link-item"><g:link controller="comment" action="toComment" params="[bookId:book.id]">to Comment</g:link></span>
					</div>
				</div>
				<div class="comment-list">
					<h5>Comments</h5>
						<g:if test="${flash.message}">
      						<div class="message">${flash.message}</div>
    					</g:if>
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
		</div>
	</body>
</html>