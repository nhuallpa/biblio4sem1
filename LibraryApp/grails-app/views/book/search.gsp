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
						  <span class="item-title"><g:link action="show" id="${book.id}">${book.name}</g:link></span>
				          <h4>ISBN: ${book.ISBN}</h4>
				          <span class="text">${book.state}</span>
				          <div class="list-operator-item">
				          		<span class="link-item"><g:link controller="reservation" action="toReserve" params="[bookId:book.id]">to Reserve</g:link></span>
				          		|
				          		<span class="link-item"><g:link controller="comment" action="toComment" params="[bookId:book.id]">to Comment</g:link></span>
				          		|
				          		<span class="link-item"><a href="#">View</a></span>
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
