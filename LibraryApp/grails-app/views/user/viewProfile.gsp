<html>
	<head>
		<meta name="layout" content="home" />
		<title>Profile: ${user?.name}</title>
	</head>
	<body>
		
		<h3 class="long-title">${user?.name}</h3>
		
		<div id="items" class="shadowed">
			<div class="inner-boundary">
<%--				<span class="text">${user.photo}</span>--%>
<%--				|--%>
				<span class="text">email: ${user?.email }</span>
				|
				<span class="text">Phone: ${user?.phone }</span>
				|
				<span class="text">Rating: ${user?.rating}</span>
				|
				<span class="text">Total Votes: ${user?.totalVotes}</span>
		</div>				
				<h4>Reservations</h4>
				<ul class="item-list">
					<g:each var="reservation" in="${user?.reservations}">
						<li class="book">
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
					<g:each var="commentDone" in="${user?.comments}">
						<li class="book">
							<h4>"${commentDone.description}"</h4>
							<p>Comment Score: ${commentDone.score}</p>
							<g:link controller="book" action="viewDetails" params="[bookId:commentDone.book.id]">${commentDone.book.name}</g:link>
							
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