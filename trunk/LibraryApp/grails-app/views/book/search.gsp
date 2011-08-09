<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="home" />
    <title>Search Books</title>
  </head>
  <body>
    <g:if test="${flash.message}">
      <div class="message">${flash.message}</div>
    </g:if>

    <div class="body">
    	<div id="items" class="shadowed">
			<div class="inner-boundary">
				<g:each in="${searchResults}" status="i" var="book">
					<ul class="item-list">
						<li class="book">
						  <g:link controller="book" action="viewDetails" params="[bookId:book.id]">
						  	<img  src="<g:createLinkTo dir="images/Book/${book.name}" file="cover.jpg" />"/>
						  </g:link>
						  <div class="book-resume-item">
							  <span class="item-title"><g:link controller="book" action="viewDetails" params="[bookId:book.id]">${book.name}</g:link></span>
					          <h4>ISBN: ${book.ISBN}</h4>
					          <span class="text">${book.state}</span><br/>
					          <span class="text">Raiting: ${book.rating}</span><br/>
					          <span class="text">Total Votes: ${book.totalVotes}</span>
						  </div>
				          <div class="list-operator-item">
				          		<span class="link-item"><g:link controller="reservation" action="toReserve" params="[bookId:book.id]">to Reserve</g:link></span>
				          		|
				          		<span class="link-item"><g:link controller="comment" action="toComment" params="[bookId:book.id]">to Comment</g:link></span>
				          </div>
				        </li>			
					</ul>
				</g:each>
			</div>
		</div>
    </div>
    <div class="paginateButtons">
      <g:paginate total="${resultCount}" params="${flash}"/>
    </div>
  </body>
</html>
