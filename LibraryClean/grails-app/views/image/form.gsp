<html>
	<head>
		<title>Upload image</title>
	</head>
	<body>
		<div id="form" class="center search">
			<g:uploadForm action="upload">
				Library id:
				<g:select name="libraryId" from="${libraryList}"
					optionKey="libraryId" optionValue="libraryId"/>
				<p/>
				Photo: <input name="photo" type="file" />
				<g:actionSubmit value="upload" value="Upload"/>
			</g:uploadForm>
		</div>	
	</body>
</html>