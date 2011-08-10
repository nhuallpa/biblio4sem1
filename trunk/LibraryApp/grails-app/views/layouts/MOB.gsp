<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="${resource(dir:'css',file:'style.css')}" />
        <link rel="stylesheet" href="${resource(dir:'css',file:'cssreset.css')}" />
    
        <g:javascript src="ClientCore.js"/>
<%--        <g:javascript src="functions.js"/>--%>
        <g:javascript src="jquery/jquery-1.4.1.min.js"/>
        <g:javascript src="jquery/components/paginator.js"/>
        <g:javascript>
        	$(document).ready(function(){
                $(function(){ itemsPerPage = 9; $("#resultados").pagination(); });
            });

        </g:javascript>

		<g:layoutHead />

        <title><g:layoutTitle default="The Map of Books" /></title>
    </head>
    <body>

        <div id="encabezado">
            <div class="centrar">
                <h1 id="logo">The Map Of Books</h1>
                <div id="bloque-derecho">
                    <div id="usuario">
                        <a id="login-button" href="#">Login<span class="icon icon-login"></span></a>
                        <a id="registrarse-button" href="#">Registrarse</a>
                    </div>
                    <div id="filtros">
                        <input id="buscar" class="icon-buscar"name="" value="buscar" />
                        <select id="categorias" name="categorias">
                            <option name="todos">Todos</option>
                            <option name="2">Books</option>
                            <option name="3">Librarys</option>
                            <option name="4">Nearly</option>
                        </select>
                    </div>
                </div>
            </div>
        </div>

        <div id="cuerpo">
            <g:layoutBody />
        </div>
        <div id="fondo-popup"></div>
        <div id="login" class="popup">
            <div class="head">
                <span class="icon icon-login"></span><span>Login</span>
                <a href="#" id="boton-cerrar" class="icon icon-close boton-cerrar"></a>
            </div>
            <div class="body">
                <form name="login" method="#" action="#">
                    <span>Usuario</span><br/>
                    <input type="text" name="user"/><br/>
                    <span>Contraseña</span><br/>
                    <input type="password" name="password"/><br/>
                    <input type="submit" value="Entrar"/>
                </form>
            </div>
            <div class="footer">
                <a href="#" id="boton-recuperar-pass">Recuperar Contraseña</a>
                <a href="#" id="boton-registrarse">Registrarse</a>
            </div>
        </div>
        <div id="registrarse" class="popup">
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