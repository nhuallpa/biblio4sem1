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
    <div class="paginateButtons">
      <g:paginate total="${resultCount}" params="${flash}" max="9" />
    </div>
	<ul id="resultados" class="columns">
  		<g:each in="${searchResults}" status="i" var="book">
  			<li class="libro">
			    <div class="imagen"><img  src="<g:createLinkTo dir="images/Book/${book.name}" file="cover.jpg"  />" alt="images/Book/${book.name}" title="images/Book/${book.name}" width="122px" height="180px"/></div>
			    <ul class="detalles">
			        <li class="titulo">Title ${book.name}</li>
			        <li class="autor">Author: ${book.author}</li>
			        <li class="votos"><span class="icon icon-votos"></span>${book.rating}</li>
			        <li class="comentarios"><span class="icon icon-comments"></span>${book.totalVotes}</li>
			        <li class="verDetalles"><a href="${createLink(controller:'book', action:'viewDetails', params:[bookId:book.id])}">see details</a></li>
			    </ul>                
			</li>
  		</g:each>
	</ul>
	</body>
</html>
