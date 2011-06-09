<html>
	<head>
		<title>Search</title>
	</head>
	<body>
		<div id="form" class="center search">
			<g:form controller="home" action="index">
				<dt>Title</dt>
				<dd><g:textField name="title"/></dd>
				<dd>
					<g:actionSubmit value="search"/>
				</dd>
			</g:form>
		</div>	
	</body>
</html>