<html>
  <head>
    <meta name="layout" content="home" />
    <title>Books Store</title>
    <g:javascript src="jquery/components/paginator.js"/>
    <g:javascript >
    	    $(document).ready(function(){
                $(function(){ itemsPerPage = 9; $("#resultados").pagination(); });
            });
    </g:javascript>
  </head>
  <body>
  	<ul id="resultados" class="columns">
  		<g:each in="${listOfBooks}" status="i" var="book">
  			<li class="libro">
			    <div class="imagen"><img  src="<g:createLinkTo dir="images/Book/${book.name}" file="cover.jpg"  />" alt="images/Book/${book.name}" title="images/Book/${book.name}" width="122px" height="180px"/></div>
			    <ul class="detalles">
			        <li class="titulo">Titulo ${book.name}</li>
			        <li class="autor">Autor: ${book.author}</li>
			        <li class="votos"><span class="icon icon-votos"></span>${book.rating}</li>
			        <li class="comentarios"><span class="icon icon-comments"></span>${book.totalVotes}</li>
			        <li class="verDetalles"><a href="${createLink(controller:'book', action:'viewDetails', params:[bookId:book.id])}">ver detalles</a></li>
			    </ul>                
			</li>
  		</g:each>
	 </ul>
  	<div id="book-in-top-container">
  		<h4 class="title-section">Top of Book</h4>
  		<ul id="resultados-book-in-top-list" class="columns">
	  		<g:each in="${booksInTopFive}" status="i" var="book">
	  			<li class="libro">
				    <div class="imagen"><img  src="<g:createLinkTo dir="images/Book/${book.name}" file="cover.jpg"  />" alt="images/Book/${book.name}" title="images/Book/${book.name}" width="122px" height="180px"/></div>
				    <ul class="detalles">
				        <li class="titulo">Titulo ${book.name}</li>
				        <li class="autor">Autor: ${book.author}</li>
				        <li class="votos"><span class="icon icon-votos"></span>${book.rating}</li>
				        <li class="comentarios"><span class="icon icon-comments"></span>${book.totalVotes}</li>
				        <li class="verDetalles"><a href="${createLink(controller:'book', action:'viewDetails', params:[bookId:book.id])}">ver detalles</a></li>
				    </ul>                
				</li>
	  		</g:each>
		 </ul>
  	</div>
  </body>
</html>