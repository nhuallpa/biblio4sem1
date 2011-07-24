<html>
  <head>
    <meta name="layout" content="home" />
    <title>Books Store</title>
  </head>
  <body>
  	<h4>Carrucel de libros</h4>
  	<p>
  		Aqui van todos los libros con una vista copada
  	</p>
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