<html>
	<head>
		<meta name="layout" content="home" />
		<title>To Comment</title>
					<resource:rating />
	</head>
	<body>
		
		<h3 class="long-title">To Comment</h3>
		<div id="items" class="shadowed">
			<div class="inner-boundary">
			<h4>${book?.name}</h4>
			
			<g:link controller="book" action="viewDetails" params="[bookId:book.id]"><img  src="<g:createLinkTo dir="images/Book/${book.name}" file="cover.jpg" />"/></g:link>
<%--			<g:link controller="book" action="viewDetails" params="[bookId:book.id]">${book?.name}</g:link>--%>
			<p>ISBN: ${book?.ISBN}</p>

			<g:form controller="comment" action="toCommentBook" params="[bookId:book.id]">
			
				<dl class="form-list">
					<dt class="text-form">Your comment: </dt>
					<dd>
						<span class="input big"><g:textField name="newComment"></g:textField></span>
					</dd>
				</dl>
				<richui:rating dynamic="true" units="5" rating="${rating}" noAjax="true" inputId=${params.rating} } />
				<div class="buttons">
					<g:submitButton name="toComment" />
				</div>
			</g:form>
			</div>			
		</div>
	</body>
</html>