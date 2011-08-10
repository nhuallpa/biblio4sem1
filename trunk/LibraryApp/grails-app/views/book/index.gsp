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
  	<h4>Carrucel de libros</h4>
  	<ul id="resultados" class="columns">
	        <li class="libro">
	            <div class="imagen"><img  src="<g:createLinkTo dir="images/tapas" file="01.jpg" />" alt="01" title="01" width="122px" height="180px"/></div>
	            <ul class="detalles">
	                <li class="titulo">Titulo TituloTitulo</li>
	                <li class="autor">Autor: asd</li>
	                <li class="votos"><span class="icon icon-votos"></span>100</li>
	                <li class="comentarios"><span class="icon icon-comments"></span>100</li>
	                <li class="verDetalles"><a href="detalles.html">ver detalles</a></li>
	            </ul>                
	        </li>
	        <li class="libro">
				<div class="imagen"><img  src="<g:createLinkTo dir="images/tapas" file="02.jpg" />" alt="02" title="01" width="122px" height="180px"/></div>
	            <ul class="detalles">
	                <li class="titulo">Titulo</li>
	                <li class="autor">Autor: asd</li>
	                <li class="votos"><span class="icon icon-votos"></span>100</li>
	                <li class="comentarios"><span class="icon icon-comments"></span>100</li>
	                <li class="verDetalles"><a href="#">ver detalles</a></li>
	            </ul>                
	        </li>
	        <li class="libro">
	        	<div class="imagen"><img  src="<g:createLinkTo dir="images/tapas" file="03.jpg" />" alt="03" title="01" width="122px" height="180px"/></div>
	            <ul class="detalles">
	                <li class="titulo">Asdasd</li>
	                <li class="autor">Autor: asd</li>
	                <li class="votos"><span class="icon icon-votos"></span>100</li>
	                <li class="comentarios"><span class="icon icon-comments"></span>100</li>
	                <li class="verDetalles"><a href="#">ver detalles</a></li>
	            </ul>                
	        </li>
	   		<li class="libro">
	            <div class="imagen"><img  src="<g:createLinkTo dir="images/tapas" file="01.jpg" />" alt="01" title="01" width="122px" height="180px"/></div>
	            <ul class="detalles">
	                <li class="titulo">Titulo TituloTitulo</li>
	                <li class="autor">Autor: asd</li>
	                <li class="votos"><span class="icon icon-votos"></span>100</li>
	                <li class="comentarios"><span class="icon icon-comments"></span>100</li>
	                <li class="verDetalles"><a href="detalles.html">ver detalles</a></li>
	            </ul>                
	        </li>
	        <li class="libro">
				<div class="imagen"><img  src="<g:createLinkTo dir="images/tapas" file="02.jpg" />" alt="02" title="01" width="122px" height="180px"/></div>
	            <ul class="detalles">
	                <li class="titulo">Titulo</li>
	                <li class="autor">Autor: asd</li>
	                <li class="votos"><span class="icon icon-votos"></span>100</li>
	                <li class="comentarios"><span class="icon icon-comments"></span>100</li>
	                <li class="verDetalles"><a href="#">ver detalles</a></li>
	            </ul>                
	        </li>
	        <li class="libro">
	        	<div class="imagen"><img  src="<g:createLinkTo dir="images/tapas" file="03.jpg" />" alt="03" title="01" width="122px" height="180px"/></div>
	            <ul class="detalles">
	                <li class="titulo">Asdasd</li>
	                <li class="autor">Autor: asd</li>
	                <li class="votos"><span class="icon icon-votos"></span>100</li>
	                <li class="comentarios"><span class="icon icon-comments"></span>100</li>
	                <li class="verDetalles"><a href="#">ver detalles</a></li>
	            </ul>                
	        </li>
	        <li class="libro">
	            <div class="imagen"><img  src="<g:createLinkTo dir="images/tapas" file="01.jpg" />" alt="01" title="01" width="122px" height="180px"/></div>
	            <ul class="detalles">
	                <li class="titulo">Titulo TituloTitulo</li>
	                <li class="autor">Autor: asd</li>
	                <li class="votos"><span class="icon icon-votos"></span>100</li>
	                <li class="comentarios"><span class="icon icon-comments"></span>100</li>
	                <li class="verDetalles"><a href="detalles.html">ver detalles</a></li>
	            </ul>                
	        </li>
	        <li class="libro">
				<div class="imagen"><img  src="<g:createLinkTo dir="images/tapas" file="02.jpg" />" alt="02" title="01" width="122px" height="180px"/></div>
	            <ul class="detalles">
	                <li class="titulo">Titulo</li>
	                <li class="autor">Autor: asd</li>
	                <li class="votos"><span class="icon icon-votos"></span>100</li>
	                <li class="comentarios"><span class="icon icon-comments"></span>100</li>
	                <li class="verDetalles"><a href="#">ver detalles</a></li>
	            </ul>                
	        </li>
	        <li class="libro">
	        	<div class="imagen"><img  src="<g:createLinkTo dir="images/tapas" file="03.jpg" />" alt="03" title="01" width="122px" height="180px"/></div>
	            <ul class="detalles">
	                <li class="titulo">Asdasd</li>
	                <li class="autor">Autor: asd</li>
	                <li class="votos"><span class="icon icon-votos"></span>100</li>
	                <li class="comentarios"><span class="icon icon-comments"></span>100</li>
	                <li class="verDetalles"><a href="#">ver detalles</a></li>
	            </ul>                
	        </li>
	        <li class="libro">
	            <div class="imagen"><img  src="<g:createLinkTo dir="images/tapas" file="01.jpg" />" alt="01" title="01" width="122px" height="180px"/></div>
	            <ul class="detalles">
	                <li class="titulo">Titulo TituloTitulo</li>
	                <li class="autor">Autor: asd</li>
	                <li class="votos"><span class="icon icon-votos"></span>100</li>
	                <li class="comentarios"><span class="icon icon-comments"></span>100</li>
	                <li class="verDetalles"><a href="detalles.html">ver detalles</a></li>
	            </ul>                
	        </li>
	        <li class="libro">
				<div class="imagen"><img  src="<g:createLinkTo dir="images/tapas" file="02.jpg" />" alt="02" title="01" width="122px" height="180px"/></div>
	            <ul class="detalles">
	                <li class="titulo">Titulo</li>
	                <li class="autor">Autor: asd</li>
	                <li class="votos"><span class="icon icon-votos"></span>100</li>
	                <li class="comentarios"><span class="icon icon-comments"></span>100</li>
	                <li class="verDetalles"><a href="#">ver detalles</a></li>
	            </ul>                
	        </li>
	        <li class="libro">
	        	<div class="imagen"><img  src="<g:createLinkTo dir="images/tapas" file="03.jpg" />" alt="03" title="01" width="122px" height="180px"/></div>
	            <ul class="detalles">
	                <li class="titulo">Asdasd</li>
	                <li class="autor">Autor: asd</li>
	                <li class="votos"><span class="icon icon-votos"></span>100</li>
	                <li class="comentarios"><span class="icon icon-comments"></span>100</li>
	                <li class="verDetalles"><a href="#">ver detalles</a></li>
	            </ul>                
	        </li>
	        <li class="libro">
	            <div class="imagen"><img  src="<g:createLinkTo dir="images/tapas" file="01.jpg" />" alt="01" title="01" width="122px" height="180px"/></div>
	            <ul class="detalles">
	                <li class="titulo">Titulo TituloTitulo</li>
	                <li class="autor">Autor: asd</li>
	                <li class="votos"><span class="icon icon-votos"></span>100</li>
	                <li class="comentarios"><span class="icon icon-comments"></span>100</li>
	                <li class="verDetalles"><a href="detalles.html">ver detalles</a></li>
	            </ul>                
	        </li>
	        <li class="libro">
				<div class="imagen"><img  src="<g:createLinkTo dir="images/tapas" file="02.jpg" />" alt="02" title="01" width="122px" height="180px"/></div>
	            <ul class="detalles">
	                <li class="titulo">Titulo</li>
	                <li class="autor">Autor: asd</li>
	                <li class="votos"><span class="icon icon-votos"></span>100</li>
	                <li class="comentarios"><span class="icon icon-comments"></span>100</li>
	                <li class="verDetalles"><a href="#">ver detalles</a></li>
	            </ul>                
	        </li>
	        <li class="libro">
	        	<div class="imagen"><img  src="<g:createLinkTo dir="images/tapas" file="03.jpg" />" alt="03" title="01" width="122px" height="180px"/></div>
	            <ul class="detalles">
	                <li class="titulo">Asdasd</li>
	                <li class="autor">Autor: asd</li>
	                <li class="votos"><span class="icon icon-votos"></span>100</li>
	                <li class="comentarios"><span class="icon icon-comments"></span>100</li>
	                <li class="verDetalles"><a href="#">ver detalles</a></li>
	            </ul>                
	        </li>
	        <li class="libro">
	            <div class="imagen"><img  src="<g:createLinkTo dir="images/tapas" file="01.jpg" />" alt="01" title="01" width="122px" height="180px"/></div>
	            <ul class="detalles">
	                <li class="titulo">Titulo TituloTitulo</li>
	                <li class="autor">Autor: asd</li>
	                <li class="votos"><span class="icon icon-votos"></span>100</li>
	                <li class="comentarios"><span class="icon icon-comments"></span>100</li>
	                <li class="verDetalles"><a href="detalles.html">ver detalles</a></li>
	            </ul>                
	        </li>
	        <li class="libro">
				<div class="imagen"><img  src="<g:createLinkTo dir="images/tapas" file="02.jpg" />" alt="02" title="01" width="122px" height="180px"/></div>
	            <ul class="detalles">
	                <li class="titulo">Titulo</li>
	                <li class="autor">Autor: asd</li>
	                <li class="votos"><span class="icon icon-votos"></span>100</li>
	                <li class="comentarios"><span class="icon icon-comments"></span>100</li>
	                <li class="verDetalles"><a href="#">ver detalles</a></li>
	            </ul>                
	        </li>
	        <li class="libro">
	        	<div class="imagen"><img  src="<g:createLinkTo dir="images/tapas" file="03.jpg" />" alt="03" title="01" width="122px" height="180px"/></div>
	            <ul class="detalles">
	                <li class="titulo">Asdasd</li>
	                <li class="autor">Autor: asd</li>
	                <li class="votos"><span class="icon icon-votos"></span>100</li>
	                <li class="comentarios"><span class="icon icon-comments"></span>100</li>
	                <li class="verDetalles"><a href="#">ver detalles</a></li>
	            </ul>                
	        </li>
	        <li class="libro">
	            <div class="imagen"><img  src="<g:createLinkTo dir="images/tapas" file="01.jpg" />" alt="01" title="01" width="122px" height="180px"/></div>
	            <ul class="detalles">
	                <li class="titulo">Titulo TituloTitulo</li>
	                <li class="autor">Autor: asd</li>
	                <li class="votos"><span class="icon icon-votos"></span>100</li>
	                <li class="comentarios"><span class="icon icon-comments"></span>100</li>
	                <li class="verDetalles"><a href="detalles.html">ver detalles</a></li>
	            </ul>                
	        </li>
	        <li class="libro">
				<div class="imagen"><img  src="<g:createLinkTo dir="images/tapas" file="02.jpg" />" alt="02" title="01" width="122px" height="180px"/></div>
	            <ul class="detalles">
	                <li class="titulo">Titulo</li>
	                <li class="autor">Autor: asd</li>
	                <li class="votos"><span class="icon icon-votos"></span>100</li>
	                <li class="comentarios"><span class="icon icon-comments"></span>100</li>
	                <li class="verDetalles"><a href="#">ver detalles</a></li>
	            </ul>                
	        </li>
	        <li class="libro">
	        	<div class="imagen"><img  src="<g:createLinkTo dir="images/tapas" file="03.jpg" />" alt="03" title="01" width="122px" height="180px"/></div>
	            <ul class="detalles">
	                <li class="titulo">Asdasd</li>
	                <li class="autor">Autor: asd</li>
	                <li class="votos"><span class="icon icon-votos"></span>100</li>
	                <li class="comentarios"><span class="icon icon-comments"></span>100</li>
	                <li class="verDetalles"><a href="#">ver detalles</a></li>
	            </ul>                
	        </li>
	    </ul>
  	<div id="book-in-top-container">
  		<h4>Top 5 of Book</h4>
  		<div id="book-in-top-list">
  			<g:each	var="book" in="${booksInTopFive}">
	  			<div class="book-in-top-item">
	  				<img  src="<g:createLinkTo dir="images/Book/${book.name}" file="cover.jpg" />"/>
	  				<p id="title">Title: <g:link controller="book" action="viewDetails" params="[bookId:book.id]">${book.name}</g:link></p>
	  				<p id="author">Author: ${book.author}</p>
	  				<p id="description">Description: ${book.description}</p>
	  				<p id="rating">Estellas: ${book.rating}</p>
	  				<p id="total-votes">Total Votes: ${book.totalVotes}</p>
	  			</div>
  			</g:each>
  		</div>
  	</div>
  </body>
</html>