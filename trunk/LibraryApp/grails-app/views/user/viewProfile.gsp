<html>
	<head>
		<meta name="layout" content="home" />
		<title>Profile: ${userProfile?.name}</title>
	</head>
	<body>
		
		<h3 class="long-title">${userProfile?.name}</h3>
		
		<div id="items" class="shadowed">
			<div class="inner-boundary">
				<span class="text">email: ${userProfile?.email }</span>
				|
				<span class="text">Phone: ${userProfile?.phone }</span>
				|
				<span class="text">Rating: ${userProfile?.rating}</span>
				|
				<span class="text">Total Votes: ${userProfile?.totalVotes}</span>
		</div>				
				<ul class="item-list">
					<g:each var="tag_fav" in="${userProfile?.typesFav}">
						<li>
							<span class="text">${tag_fav.name}</span>
					    </li>
					</g:each>
				</ul>
				
				<h4>Reservations</h4>
				<ul class="item-list">
					<g:each var="reservation" in="${userProfile?.reservations}">
						<li>
							<span class="text"><g:link controller="book" action="viewDetails" params="[bookId:reservation.book.id]">${reservation.book.name}</g:link></span>
							|
							<span class="text">${reservation.reservationDate.toString()}</span>
<%--							<p>${reservation.library.name}</p>  FALTA EL NOMBRE...--%>
					    </li>
					</g:each>
				</ul>
			<div class="inner-boundary">
				<h4>Comments Done</h4>
				<ul class="item-list">
					<g:each var="commentDone" in="${userProfile?.commentsDone}">
						<li>
							<g:link controller="book" action="viewDetails" params="[bookId:commentDone.book.id]">${commentDone.book.name}</g:link>
							<h4>"${commentDone.description}"</h4>
							<p>Comment Score: ${commentDone.score}</p>
					    </li>
					</g:each>
				</ul>
				
<%--				<ul class="item-list">--%>
<%--					<g:each var="commentRv" in="${user?.commentsRcvd}">--%>
<%--						<li class="book">--%>
<%--							<h4>"${commentRv.description}"</h4>--%>
<%--							<span class="text"><g:link controller="user" action="viewProfile" params="[userId:commentRv.sourceUser.id]">User: ${commentRv.sourceUser.name} [${commentRv.sourceUser.rating}]</g:link></span>--%>
<%--							<p>${commentRv.date}</p>--%>
<%--							<p>Comment Score: ${commentRv.score}</p>--%>
<%--					    </li>--%>
<%--					</g:each>--%>
<%--				</ul>--%>
			</div>
		</div>
	</body>
</html>