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
				<span class="title_fav">Favorite Genres</span>	
				<fieldset id="types_fav">
					<fieldset>
						<g:checkBox name="type_accion" value="${false}" />
						<label for="checkbox">Action</label>
					</fieldset>	
					<fieldset>
						<g:checkBox name="type_drama" value="${false}" />
						<label for="checkbox">Drama</label>					
					</fieldset>	
					<fieldset>
						<g:checkBox name="type_ficcion" value="${false}" />
						<label for="checkbox">Fiction</label>					
					</fieldset>	
					<fieldset>
						<g:checkBox name="type_novela" value="${false}" />
						<label for="checkbox">Novels</label>					
					</fieldset>
					<fieldset>
						<g:checkBox name="type_adventures" value="${false}" />
						<label for="checkbox">Adventures</label>					
					</fieldset>
				</fieldset>													
			</div>
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
						<label for="email">Email</label>
						<g:textField name="email"></g:textField>
					</fieldset>
					<fieldset>
						<label for="email">Photo</label>
						<g:textField name="photo"></g:textField>
					</fieldset>			
					<fieldset>
						<label for="email">Phone</label>
						<g:textField name="phone"></g:textField>
					</fieldset>		
					<fieldset>
						<g:submitButton id="login" name="Register" />
					</fieldset>																				
				</fieldset>
			</g:form>
			
	</body>
</html>