<html>
	<head>
		<title>Upload image</title>
	</head>
	<body>
		<div id="listLibrarys" class="center search">
			<g:each var="library" in="${libraryList}">
				libraryId: <br/>
				<p>libray name <strong>${library.name}</strong></p>
				
				<img src="<g:createLinkTo dir="images/Library/${library.libraryId}" file="photo.jpg"/>"/>
				
					
			</g:each>
		</div>	
	</body>
</html>