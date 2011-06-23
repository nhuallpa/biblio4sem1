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
			<g:form controller="comment" action="toCommentBook" params="[bookId:book?.id]">
					<dl class="form-list">
						<dt class="text-form">Your comment</dt>
							<dd>
								<span class="input big"><g:textField name="newComment"></g:textField></span>
							</dd>
					</dl>
					<div class="buttons">
						<g:submitButton name="toComment" />
					</div>
			</g:form>
			

<%--				<ul class="item-list">--%>
<%--					<g:each var="comment" in="${comments}">--%>
<%--						<li class="book">--%>
<%--							--%>
<%--							 <g:link controller="book" action="show" id="${comment.id}">${comment.description}</g:link>--%>
<%--							 <p>Score: ${comment.score}</p>--%>
<%----%>
<%--					    </li>--%>
<%--					</g:each>	--%>
<%--				</ul>--%>
			</div>
<%--			<div class="buttons">--%>
<%--				<g:submitButton name="To Comment">--%>
<%--					<g:link controller="comment" action="viewMyComments" params="[bookId:book?.id,description]"></g:link>--%>
<%--				</g:submitButton>--%>
<%--			</div>--%>
			
		</div>
	</body>
</html>