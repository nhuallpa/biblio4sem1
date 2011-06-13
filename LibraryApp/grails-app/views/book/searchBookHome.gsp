<html>
	<head>
		<meta name="layout" content="home" />
		<title>Search</title>
	</head>
	<body>
		<div id="form" class="center search">
			<g:form controller="book" action="searchBookByTitle">
				<dt>Title</dt>
				<dd><g:textField name="title"/></dd>
				<dd>
					<g:actionSubmit value="search"/>
				</dd>
			</g:form>
		</div>	
		<g:if test="${bookList}">
			<p class="text-title-section">Book found. 10 of 100</p>
			<div class="items">
				<g:each var="book" in="bookList">
					<p class="text"><strong>${book.title}</strong></p>
				</g:each>
			</div>
		</g:if>
	</body>
</html>