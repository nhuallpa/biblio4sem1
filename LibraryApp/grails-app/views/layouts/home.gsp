<!DOCTYPE html>
<html>
	<head>
		<title><g:layoutTitle default="Home" /></title>
		<link rel="stylesheet" href="${resource(dir:'css',file:'cssreset.css')}" />
		<link rel="stylesheet" href="${resource(dir:'css',file:'style.css')}" />
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js?ver=1.4.2"></script>
		<g:javascript src="ClientCore.js"/>
		<g:layoutHead />
		</head>
	<body>
		<div id="encabezado">
            <div class="centrar">
                <h1 id="logo">The Map Of Books</h1>
                <div id="bloque-derecho">
<%--                	<g:link class="botones_header controller="book"  action="index">Books store</g:link>--%>
					<div class="botones_header">
						<a id="book_store" href="${createLink(controller:'book', action:'index')}">Books store</a>
                		<a id="librarys" href="${createLink(controller:'library', action:'gmaps')}">Librarys</a>
					</div>

                    <div id="usuario">
                    		
                    	<g:if test="${session.user}">
                    		<a id="logout-button" href="${createLink(controller:'user', action:'logout')}">Logout<span class="icon icon-login"></span></a>
                    		<a id="name-to-profile" href="${createLink(controller:'user', action:'viewProfile', params:[userId:session.user.id])}">${session.user.name}</a>
                    	</g:if>
                    	<g:else>
                    		<a id="login-button" href="#">Login<span class="icon icon-login"></span></a>
                    		<a id="registrarse-button" href="${createLink(controller:'user', action:'registration')}">Registrarse</a>
                    	</g:else>
                    </div>
                    <div id="filtros">
			  			<g:form url='[controller: "book", action: "search"]'
			          			id="searchableForm"
			          			name="searchableForm"
			          			method="get">
			    				<richui:autoComplete id="buscar" class="icon-buscar" name="q" value="${params.q}" action="${createLinkTo('dir': 'book/searchAJAX')}" />
			    				<select id="categorias" name="categorias">
		                            <option name="todos">Todos</option>
		                            <option name="2">Books</option>
		                            <option name="3">Librarys</option>
		                            <option name="4">Nearly</option>
		                        </select>
			  			</g:form>						
                    </div>
                </div>
            </div>
        </div>
		<div id="cuerpo">
			<div class="container">
				<div class="col-s-content">
					<g:layoutBody />
				</div><%--
				<div class="large-sidebar">
					<ul>
						<g:if test="${session.user}">
							<g:if test="${session.user.name.equals('admin') }">
								<li><g:link controller="home"  action="administration">Administration</g:link></li>
							</g:if>
						</g:if>
					</ul>
					<g:if test="${session.user}">
						<ul class="menu-vert">
							<li><g:link controller="reservation" action="viewMyReservation">My reservation</g:link></li>
							<li><g:link controller="comment" action="viewMyComments">My Comments</g:link></li>
							<li><a href="#">Recommendation for me</a></li>
						</ul>
					</g:if>
				</div>	
			--%></div>
		</div>
		<div id="fondo-popup"></div>
        <div id="login" class="popup">
            <div class="head">
                <span class="icon icon-login"></span><span>Login</span>
                <a href="#" id="boton-cerrar" class="icon icon-close boton-cerrar"></a>
            </div>
            <div class="body">
             	<g:form name="login" controller="user" action="login">
					<fieldset id="body">
						<fieldset>
							<label for="email">User</label>
							<g:textField name="userId"></g:textField>
						</fieldset>
						<fieldset>
							<label for="password">Password</label>
							<g:passwordField name="password"></g:passwordField>
						</fieldset>
						<g:submitButton id="login" name="Sing in" />
					</fieldset>
					

				</g:form>
            </div>
            <div class="footer">
                <a href="#" id="boton-recuperar-pass">Recuperar Contraseña</a>
                <a href="${createLink(controller:'user', action:'registration')}" id="boton-registrarse">Registrarse</a>
            </div>
        </div>
        <%--<div id="registrarse" class="popup">
            <div class="head">
                <span>Registrarse</span>
                <a href="#" id="boton-cerrar" class="icon icon-close boton-cerrar"></a>
            </div>
            <div class="body">
                <form name="registrase" method="#" action="#">
                    <span>Usuario</span><br/>
                    <input type="text" name="user"/><br/>
                    <span>Contraseña</span><br/>
                    <input type="password" name="password"/><br/>
                    <span>Repetir Contraseña</span><br/>
                    <input type="password" name="re-password"/><br/>
                    <input type="submit" value="Enviar"/>
                </form>
            </div>
        </div>
        --%>
        <div id="recuperar" class="popup">
            <div class="head">
                <span>Recuperar Contraseña</span>
                <a href="#" id="boton-cerrar"class="icon icon-close"></a>
            </div>
            <div class="body">
                <form name="recuperar" method="#" action="#">
                    <span>Mail</span><br/>
                    <input type="text" name="mail"/><br/>
                    <input type="submit" value="Entrar"/>
                </form>
            </div>
        </div>
	</body>
</html>