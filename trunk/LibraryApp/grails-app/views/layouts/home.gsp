<!DOCTYPE html>
<html>
	<head>
		<title><g:layoutTitle default="Home" /></title>
		<link rel="stylesheet" href="${resource(dir:'css',file:'home.css')}" />
		<g:layoutHead />
	</head>
	<body>
		<div class="header">
			<div class="container">
				
				<h1><g:link url="/Library">Library</g:link></h1>
			</div>
		</div>
		<div id="content">
			<div class="container">
				<div class="col-s-content">
					<g:layoutBody />
				</div>
				<div class="large-sidebar">
					<div class="inner-boundary last-item">
						<div class="inner-border author-item">
							<g:if test="${session.user}">
								<div>
								<span class="text-form">${session.user.name}</span> <br/>
								<span class="text-form">points: 3 Stars</span> <br/>
								<span class="text-form"><g:link controller="user" action="logout">logout</g:link></span><br/>
								 <br/>	
								</div>
								<ul class="menu-vert">
									<li><g:link controller="reservation" action="viewMyReservation">My reservation</g:link></li>
									<li><g:link controller="comment" action="viewMyComments">My Comments</g:link></li>
<%--									<li><a href="#">My Comments</a></li>--%>
									<li><a href="#">Recommendation for me</a></li>
								</ul>
							</g:if>
							<g:else>
								<g:form controller="user" action="login">
									<dl class="form-list">
										<dt class="text-form">Username</dt>
										<dd>
											<span class="input big"><g:textField name="userId"></g:textField></span>
										</dd>
										<dt class="text-form">Password</dt>
										<dd>
											<span class="input big"><g:passwordField name="password"></g:passwordField></span>
										</dd>
									</dl>
									<div class="buttons">
											<g:submitButton name="login" />
									</div>
								</g:form>
							</g:else>
						</div>
					</div>
					<div class="inner-boundary last-item">
						<div class="inner-border author-item">
							<ul class="menu-vert">
								<li><a href="#">Categorys</a></li>
								<li><a href="#">Librarys</a></li>
								<li><a href="#">Tops of Books</a></li>
							</ul>
						</div>
					</div>		
				</div>	
			</div>
		</div>
		<div class="footer">
			<div class="container">
				<p>Following</p>
			</div>
		</div>	
	</body>
</html>