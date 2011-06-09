<html>
	<head>
		<title>Upload image</title>
	</head>
	<body>
		<div id="listLibrarys" class="center search">
			<g:each var="library" in="${libraryList}">
				libraryId: <br/>
				<p>libray name <strong>${library.name}</strong></p>
				<g:if test="${library.photo}">
					<img src="
							<g:createLink controller='image' action='renderImage' id='${library.libraryId}'/>
							"></img>
				</g:if>
			</g:each>
		</div>	
	</body>
</html>