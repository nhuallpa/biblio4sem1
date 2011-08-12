<html>
	<head>
		<meta name="layout" content="home" />
		<title>Book View: ${book?.name}</title>
	</head>
	<body>
		<div id="detallesLibro">
                <div id="resumen">
                    <div id="libro-bloque-izquierdo">
                        <div class="imagen"><img src="<g:createLinkTo dir="images/Book/${book.name}" file="cover.jpg"/>" alt="01" title="01" width="122px" height="180px"/></div>
                        <div class="estadisticas">
                            <div class="votos"><span class="icon icon-votos"></span>${book.rating}</div>
                            <div class="comentarios"><span class="icon icon-comments"></span>${book.totalVotes}</div>
                        </div>
                    </div>
                    <div id="libro-bloque-derecho">
                        <div class="datos">
                            <span class="titulo">${book.name}</span><br/>
                            <span class="autor">${book.author}</span><br/>
                            <span class="isbn">${book?.ISBN}</span>
                        </div>
                        <p class="descripcion">${book.description}</p>
                        <div class="barra">
                            <a href="#" id="votar" class="boton">Votar</a>
                            <a href="#" id="buscar-librerias" class="boton">Buscar Librerias</a>
                            <a href="#" id="buscar-listado" class="boton">Ver Listado</a>
                            <a href="#" id="buscar-mapa" class="boton">Ver Mapa</a>
                        </div>
                    </div>
                </div>
                <div id="mapa">
                    <div id="map_canvas">

                    </div>
                </div>
                <div id="lista-de-librerias">
                    <table>
                        <tr>
                            <td><b>Nombre de Libreria</b></td>
                            <td>Direccion Corrientes 485<span class="icon icon-marker"></span></td>
                            <td>46218091<span class="icon icon-cell"></span></td>
                            <td>nombre@libreria.com.ar<span class="icon icon-mail"></td>
                        </tr>
                        <tr>
                            <td><b>Nombre de Libreria</b></td>
                            <td>Direccion Corrientes 485<span class="icon icon-marker"></span></td>
                            <td>46218091<span class="icon icon-cell"></span></td>
                            <td>nombre@libreria.com.ar<span class="icon icon-mail"></td>
                        </tr>
                        <tr>
                            <td><b>Nombre de Libreria</b></td>
                            <td>Direccion Corrientes 485<span class="icon icon-marker"></span></td>
                            <td>46218091<span class="icon icon-cell"></span></td>
                            <td>nombre@libreria.com.ar<span class="icon icon-mail"></td>
                        </tr>
                        <tr>
                            <td><b>Nombre de Libreria</b></td>
                            <td>Direccion Corrientes 485<span class="icon icon-marker"></span></td>
                            <td>46218091<span class="icon icon-cell"></span></td>
                            <td>nombre@libreria.com.ar<span class="icon icon-mail"></td>
                        </tr>
                        <tr>
                            <td><b>Nombre de Libreria</b></td>
                            <td>Direccion Corrientes 485<span class="icon icon-marker"></span></td>
                            <td>46218091<span class="icon icon-cell"></span></td>
                            <td>nombre@libreria.com.ar<span class="icon icon-mail"></td>
                        </tr>
                        <tr>
                            <td><b>Nombre de Libreria</b></td>
                            <td>Direccion Corrientes 485<span class="icon icon-marker"></span></td>
                            <td>46218091<span class="icon icon-cell"></span></td>
                            <td>nombre@libreria.com.ar<span class="icon icon-mail"></td>
                        </tr>
                        <tr>
                            <td><b>Nombre de Libreria</b></td>
                            <td>Direccion Corrientes 485<span class="icon icon-marker"></span></td>
                            <td>46218091<span class="icon icon-cell"></span></td>
                            <td>nombre@libreria.com.ar<span class="icon icon-mail"></td>
                        </tr>
                        <tr>
                            <td><b>Nombre de Libreria</b></td>
                            <td>Direccion Corrientes 485<span class="icon icon-marker"></span></td>
                            <td>46218091<span class="icon icon-cell"></span></td>
                            <td>nombre@libreria.com.ar<span class="icon icon-mail"></td>
                        </tr>
                        <tr>
                            <td><b>Nombre de Libreria</b></td>
                            <td>Direccion Corrientes 485<span class="icon icon-marker"></span></td>
                            <td>46218091<span class="icon icon-cell"></span></td>
                            <td>nombre@libreria.com.ar<span class="icon icon-mail"></td>
                        </tr>
                        <tr>
                            <td><b>Nombre de Libreria</b></td>
                            <td>Direccion Corrientes 485<span class="icon icon-marker"></span></td>
                            <td>46218091<span class="icon icon-cell"></span></td>
                            <td>nombre@libreria.com.ar<span class="icon icon-mail"></td>
                        </tr>
                        <tr>
                            <td><b>Nombre de Libreria</b></td>
                            <td>Direccion Corrientes 485<span class="icon icon-marker"></span></td>
                            <td>46218091<span class="icon icon-cell"></span></td>
                            <td>nombre@libreria.com.ar<span class="icon icon-mail"></td>
                        </tr>
                        <tr>
                            <td><b>Nombre de Libreria</b></td>
                            <td>Direccion Corrientes 485<span class="icon icon-marker"></span></td>
                            <td>46218091<span class="icon icon-cell"></span></td>
                            <td>nombre@libreria.com.ar<span class="icon icon-mail"></td>
                        </tr>
                    </table>
                </div>
                <g:if test="${session.user}">
                	<div class="barra">
	                    <span id="comentar">Comentar</span>
	                </div>
	                <div id="nuevo-comentario">
	                    <g:form id="new-comment" controller="comment" action="addCommentToBook">
	                        <p>Write here your comment</p>
	                        <g:textArea name="commentText" id="commentText"></g:textArea>
	                        <g:hiddenField name="bookId" value="${book.id}"/>
	                        <g:submitButton name="enviar"></g:submitButton>
	                    </g:form>
	                </div>
                </g:if>
                <ul class="lista-de-comentarios">
                	<g:if test="${flash.message}">
      						<div class="message">${flash.message}</div>
    				</g:if>
                	<g:each var="comment" in="${book?.comments}">
	                    <li>
	                        <span class="icon icon-comment"></span>
	                        <span id="responder" class="icon icon-responder"></span>
	                        <p><span class="nick"> <g:link controller="user" action="viewProfile" params="[userId:comment.sourceUser.id]">${comment.sourceUser.name}</g:link> </span></p>
	                        <p>${comment.description}</p>
	                    </li>
	                </g:each>
                </ul>
            </div>
		
		<%--<h3 class="long-title">${book?.name}</h3>
		
		<div id="items" class="shadowed">
			<div class="inner-boundary">
				<div class="book-description">
					<img  src="<g:createLinkTo dir="images/Book/${book.name}" file="cover.jpg" />"/>
					<div class="details-more-info">
					<span class="text">ISBN: ${book?.ISBN}</span><br/>
					<span class="text">Subject: ${book?.subject }</span><br/>
					<span class="text">Rating: ${book?.rating }</span><br/>
					<span class="text">Total Votes: ${book?.totalVotes}</span><br/>
					<span class="text">State: ${book.state}</span><br/>
					</div>
					<div class="list-operator-item">
						<span class="link-item"><g:link controller="reservation" action="toReserve" params="[bookId:book.id]">to Reserve</g:link></span>
						|
					  	<span class="link-item"><g:link controller="comment" action="toComment" params="[bookId:book.id]">to Comment</g:link></span>
					</div>
				</div>
				<div class="comment-list">
					<h5>Comments</h5>
						<g:if test="${flash.message}">
      						<div class="message">${flash.message}</div>
    					</g:if>
					<ul class="item-list">
						<g:each var="comment" in="${book?.comments}">
							<li class="book">
								<h4>"${comment.description}"</h4>
								<span class="text"><g:link controller="user" action="viewProfile" params="[userId:comment.sourceUser.id]">User: ${comment.sourceUser.name} [${comment.sourceUser.rating}]</g:link></span>
								<p>${comment.date}</p>
								<p>Comment Score: ${comment.score}</p>
						    </li>
						</g:each>
					</ul>
				</div>
			</div>
		</div>
	--%></body>
</html>