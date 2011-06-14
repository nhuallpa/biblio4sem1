<html>
	<head>
		<meta name="layout" content="home" />
		<title>Search</title>
	</head>
	<body>
		<div id="search">
  			<g:form url='[controller: "book", action: "search"]'
          		id="searchableForm"
          		name="searchableForm"
          		method="get">
    				<g:textField name="q" value="${params.q}" />
    				<input type="submit" value="Search" />
  			</g:form>
		</div>
	</body>
</html>