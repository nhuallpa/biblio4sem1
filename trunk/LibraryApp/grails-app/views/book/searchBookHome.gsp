<html>
	<head>
		<meta name="layout" content="home" />
		<resource:autoComplete skin="default" />
		<title>Search</title>

	</head>
	<body>
		<div id="searchForm">
  			<g:form url='[controller: "book", action: "search"]'
          		id="searchableForm"
          		name="searchableForm"
          		method="get">
    				<richui:autoComplete name="q" value="${params.q}" action="${createLinkTo('dir': 'book/searchAJAX')}" />
    				<input id="searchButton" type="submit" value="Search" />	
  			</g:form>
		</div>

	</body>
</html>