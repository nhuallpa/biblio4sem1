<html>
	<head>
		<meta name="layout" content="home" />
		<title>My Comments</title>
	</head>
	<body>
		
		<h3 class="long-title">My Comments</h3>
		
		<div id="items" class="shadowed">
			<div class="inner-boundary">
				<ul class="item-list">
					<g:each var="comment" in="${comments}">
						<li class="book">
							 <dl><g:link controller="book" action="show" id="${comment.id}">${comment.description}</g:link></dl>
							  

					    </li>
					</g:each>	
				</ul>
			</div>
		</div>
	</body>
</html>