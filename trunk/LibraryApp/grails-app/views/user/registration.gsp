<html>
	<head>
		<meta name="layout" content="home" />
		<title>Registration</title>
	</head>
	<body>
		<g:if test="${flash.message}">
      		<div class="message">${flash.message}</div>
    	</g:if>
   		<h3>Registration</h3>
		<g:form class="registerForm" controller="user" action="toRegister">
			<div class="checksForm">
				<fieldset>
					<label for="country">Country<span class="require">*</span></label><br/>
					<g:textField name="country"></g:textField>
				</fieldset>
				<fieldset>
					<label for="city">City<span class="require">*</span></label><br/>
					<g:textField name="city"></g:textField>
				</fieldset>
				<fieldset>
					<label for="street">Street<span class="require">*</span></label><br/>
					<g:textField name="street"></g:textField>
				</fieldset>
			</div>
			<div class="checksForm">
				<span class="titles">Favorite Genres</span>	
				<div id="description">
					<h5>Write your interested genre by your own tags</h5>
					Example:<br/><div class="examples">literature, accion, fiction, it</div>
					<br/>
					<h5>Also, you can be specific</h5>
					Example:<br/><div class="examples">roma, borges, groovy, java</div><br/>
				</div>
				<g:textArea id="tags" name="tags"></g:textArea>												
			</div>
			<fieldset id="body">
				<fieldset>
					<label for="email">Username<span class="require">*</span></label>
					<g:textField name="user_name"></g:textField>
				</fieldset>
				<fieldset>
					<label for="password">Password<span class="require">*</span></label>
					<g:passwordField name="password1"></g:passwordField>
				</fieldset>
				<fieldset>
					<label for="password">Repeat Password<span class="require">*</span></label>
					<g:passwordField name="password2"></g:passwordField>
				</fieldset>	
				<fieldset>
					<label for="email">Email</label>
					<g:textField name="email"></g:textField>
				</fieldset>
	<%--				<fieldset>--%>
	<%--					<label for="email">Photo</label>--%>
	<%--					<g:textField name="photo"></g:textField>--%>
	<%--				</fieldset>			--%>
				<fieldset>
					<label for="email">Phone</label>
					<g:textField name="phone"></g:textField>
				</fieldset>		
				<fieldset>
					<g:submitButton name="Register" />
				</fieldset>																				
			</fieldset>
		</g:form>	
	</body>
</html>