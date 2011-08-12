<html>
	<head>
		<meta name="layout" content="home" />
		<title>Profile: ${userProfile?.name}</title>
	</head>
	<body>
		<div id="profile-details">
			<img src="<g:createLinkTo dir="images/User/" file="photo.png" />"></img>
			<div class="resumen-derecho">
				<h3 id="name">${userProfile?.name}</h3>
				<div class="user-details">
					<span class="text">email: ${userProfile?.email }</span>
					|
					<span class="text">Phone: ${userProfile?.phone }</span>
					|
				</div>				
				<h4>Favourite Genres</h4>
				<ul>
					<g:each var="tag_fav" in="${userProfile?.typesFav}">
						<li><span>${tag_fav.name}</span></li>
					</g:each>
				</ul>
			</div>
		</div>
		<g:if test="${flash.message}">
      		<div class="message">${flash.message}</div>
    	</g:if>
		<h4 class="title-section" >Reservations</h4>
		<ul class="item-list">
			<g:each var="reservation" in="${userProfile?.reservations}">
				<li class="book">
				  <g:link controller="book" action="viewDetails" params="[bookId:reservation.book.id]">
				  	<img  src="<g:createLinkTo dir="images/Book/${reservation.book.name}" file="cover.jpg" />"/>
				  </g:link>
					<span class="text"><g:link controller="book" action="viewDetails" params="[bookId:reservation.book.id]">${reservation.book.name}</g:link></span>
					|
					<span class="text">${reservation.reservationDate}</span>
					<span class="link-item"><g:link controller="reservation" action="cancelReserve" params="[bookId:reservation.book.id]">Cancel</g:link></span>
			    </li>
			</g:each>
		</ul>

		<h4 class="title-section">Comments Done</h4>
		<ul class="item-list">
			<g:each var="commentDone" in="${userProfile?.commentsDone}">
				<li class="book">
				  <g:link controller="book" action="viewDetails" params="[bookId:commentDone.book.id]">
				  		<img  src="<g:createLinkTo dir="images/Book/${commentDone.book.name}" file="cover.jpg" />"/>
				  </g:link>
				  <div class="resumen-derecho">
					  	<g:link controller="book" action="viewDetails" params="[bookId:commentDone.book.id]">${commentDone.book.name}</g:link>
						<h4>"${commentDone.description}"</h4>
						<p>Comment Score: ${commentDone.score}</p>
						 <span class="link-item"><g:link controller="comment" action="deleteComment" params="[commentId:commentDone.id,bookId:commentDone.book.id]">Delete</g:link></span>
				  </div>
			    </li>
			</g:each>
		</ul>
	</body>
</html>