<!DOCTYPE html>
<html>
	<head>
		<title><g:layoutTitle default="Home" /></title>
		<link rel="stylesheet" href="${resource(dir:'css',file:'home.css')}" />
		<g:layoutHead />
	</head>
	<body>
		<div id="header">
			<h1>Books 4 You</h1>
		</div>
		<div id="content">
			<g:layoutBody />
		</div>
		<div id="sidebar">
			<g:if test="${session.user}">
				<div>
				nhuallpa <br/>
				points: 3 Stars <br/>
				singout
				</div>
				<ul id="list_oper">
					<li>My reservation</li>
					<li>My Comments</li>
				</ul>
			</g:if>
			<g:else>
				<g:form >
					user: <br/>
					<g:textField name="userId"></g:textField> <br/>
					password: <br/>
					<g:passwordField name="password"></g:passwordField><br/>
					<g:submitButton name="login" controller="" action=""></g:submitButton>
				</g:form>
			</g:else>
		</div>	
		<div id="footer">
			<p>Following</p>
		</div>	
	</body>
</html>