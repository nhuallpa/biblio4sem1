<html>
	<head>
		<meta name="layout" content="home" />
		<title>Registration</title>
	</head>
	<body>
		<g:if test="${flash.message}">
      		<div class="message">${flash.message}</div>
    	</g:if>
			<g:form class="loginForm" controller="user" action="toRegister">
				<fieldset id="body">
					<fieldset>
						<label for="email">Username</label>
						<g:textField name="user_name"></g:textField>
					</fieldset>
					<fieldset>
						<label for="password">Password</label>
						<g:passwordField name="password1"></g:passwordField>
					</fieldset>
					<fieldset>
						<label for="password">Repeat Password</label>
						<g:passwordField name="password2"></g:passwordField>
					</fieldset>	
					<fieldset>
						<label for="email">Homepage</label>
						<g:textField name="homepage"></g:textField>
					</fieldset>
					<fieldset>
						<label for="email">Photo</label>
						<g:textField name="photo"></g:textField>
					</fieldset>			
					<fieldset>
						<label for="email">Phone</label>
						<g:textField name="phone"></g:textField>
					</fieldset>																						
					<g:submitButton id="login" name="Register" />
<%--					<label for="checkbox"><input type="checkbox" id="checkbox" />Remember me</label>--%>
				</fieldset>
			</g:form>

	</body>
</html>