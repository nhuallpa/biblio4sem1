<html>
	<head>
		<meta name="layout" content="home" />
		<title>To Comment</title>
	</head>
	<body>
		
		<h3 class="long-title">To Comment</h3>
		
		
			
		
		<div id="items" class="shadowed">
			<div class="inner-boundary">
			<h4>${book?.name}</h4>
			<p>ISBN: ${book?.ISBN}</p>
			<g:form controller="comment" action="toCommentBook" params="[bookId:book?.id, rating:rating]">
					<dl class="form-list">
						<dt class="text-form">Your comment: </dt>
							<dd>
								<span class="input big"><g:textField name="newComment"></g:textField></span>
							</dd>
					</dl>
					<div class="buttons">
						<g:submitButton name="toComment" />
					</div>
			</g:form>
<%--			<richui:rating dynamic="true" id="${book?.id}" units="5" rating="${rating}" controller="comment" action="rate"/>--%>
			</div>			
		</div>
	</body>
</html>