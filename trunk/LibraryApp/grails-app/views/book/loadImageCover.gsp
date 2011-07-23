<html>
	<head>
		<meta name="layout" content="home" />
		<title>Upload Cover for books</title>
	</head>
	<body>
		<div id="form" class="center search">
			<g:uploadForm action="upload">
				book id:
				<g:select name="id" from="${listOfBook}"
					optionKey="id" optionValue="name"/>
				<p/>
				cover: <input name="cover" type="file" />
				<g:actionSubmit value="upload" />
			</g:uploadForm>
		</div>
	</body>
</html>