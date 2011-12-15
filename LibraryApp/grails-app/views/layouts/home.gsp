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
                <a id="logo" href="${createLink(controller:'home', action:'index')}">The Map Of Books</a>
                <div id="bloque-derecho">
					<div class="botones_header">
						<a id="book_store" href="${createLink(controller:'book', action:'index')}">Books store</a>
                		<a id="librarys" href="${createLink(controller:'library', action:'gmaps')}">Librarys</a>
                		<g:if test="${session.user }">
                			<a id="awards-button" href="${createLink(controller:'award', action:'awards', params:[type:'default'])}">Awards</a>
                		</g:if>
					</div>

                    <div id="usuario">
                    		
                    	<g:if test="${session.user}">
                    		<a id="logout-button" href="${createLink(controller:'user', action:'logout')}">Logout<span class="icon icon-login"></span></a>
                    		<a id="name-to-profile" href="${createLink(controller:'user', action:'viewProfile', params:[userId:session.user.id])}">${session.user.name}</a>                    		
                    	</g:if>
                    	<g:else>
                    		<a id="login-button" href="#">Login<span class="icon icon-login"></span></a>
                    		<a id="registrarse-button" href="${createLink(controller:'user', action:'registration')}">Register</a>
                    	</g:else>
                    </div>
                    <div id="filtros">
			  			<g:form url='[controller: "book", action: "search"]'
			          			id="searchableForm"
			          			name="searchableForm"
			          			method="get">
			    				<richui:autoComplete id="buscar" class="icon-buscar" name="q" value="${params.q}" action="${createLinkTo('dir': 'book/searchAJAX')}" />
			    				<select id="categorias" name="categorias">
		                            <option name="todos">All</option>
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
				</div>
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
        <div id="edit-tags-profile" class="popup">
			<div class="head">
		        <span class="icon icon-login"></span><span>Edit tags</span>
		        <a href="#" id="boton-cerrar" class="icon icon-close boton-cerrar"></a>
		    </div>
			<div class="body">
			    <g:form controller="user" action="updateTags">
					<g:textArea name="user-tags-area"></g:textArea>
					<g:hiddenField name="idUser" value="${session?.user?.id}"/>
					<g:submitButton value="update" name="user-submit-tags"/>
				</g:form>
			</div>
		</div>
		<div id="edit-tags-book" class="popup">
			<div class="head">
		        <span class="icon icon-login"></span><span>Edit tags</span>
		        <a href="#" id="boton-cerrar" class="icon icon-close boton-cerrar"></a>
		    </div>
			<div class="body">
			    <g:form controller="book" action="updateTags">
					<g:textArea name="book-tags-area"></g:textArea>
					<g:hiddenField name="idBook" value="${book?.id}"/>
					<g:submitButton value="update" name="book-submit-tags"/>
				</g:form>
			</div>
		</div>
	</body>
	
</html>