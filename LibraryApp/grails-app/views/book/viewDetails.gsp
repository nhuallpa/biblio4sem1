<html>
	<head>
		<meta name="layout" content="home" />
		<title>Book View: ${book?.name}</title>

		<g:javascript src="jquery-1.4.1.min.js"/>
	    <g:javascript library="application" />
        <g:javascript library="jquery" />

		<script type="text/javascript" >
			$(document).ready(function(){
       			// Detecto la estrella que es presionada
    			$("li.star a").click(function(event){   
            		// Recojo el valor de la estrella       
        			valor_actual=$(this).attr("title");
            		// Cambio el estilo para mostrar la estrella seleccionada
        			$("li.current-rating").css("width", valor_actual*25);
            		// Cambio el valor del campo hidden
        			$("#bookRating").attr("value", valor_actual);
    			});
			});
		</script>
	
        <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
        <script type="text/javascript">

        var map;
        var geocoder = new google.maps.Geocoder();
        var geocoder2 = new google.maps.Geocoder();

        geocoder.geocode( { address: "Buenos Aires, Paseo Colon 850" }, function(results, status) {
            if (status == google.maps.GeocoderStatus.OK && results.length) {

              if (status != google.maps.GeocoderStatus.ZERO_RESULTS) {
                var latlng = results[0].geometry.location;

                var myOptions = {
                  zoom: 12,
                  center: latlng,
                  mapTypeId: google.maps.MapTypeId.ROADMAP
                }
                map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
                var marker = new google.maps.Marker({
                    position: latlng,
                    map: map
                });

                <g:each var="library" in="${librarys}">
        			var latlng2;
        			geocoder2.geocode( { address: "${library.getLocation().getCity()}+${library.getLocation().getStreet()}"   }, function(results2, status2) {
        				latlng2 = results2[0].geometry.location;

        				// Creating an InfoWindow object
        				var infowindow = new google.maps.InfoWindow({
        				  content: "${library.name}<br>Homepage:${library?.homepage}<br>${library?.location?.street},${library?.location?.city}<br>${library?.location?.country}"
        				});
        		     	
        				var m2 = new google.maps.Marker({
        					position: latlng2,
        					map: map
        					
        				});

        				google.maps.event.addListener(m2, 'click', function() {
        					  infowindow.open(map, m2);
        					});        				
        			});  			
        		</g:each>
              }              
            } else { alert("Geocode was unsuccessful due to: " + status); }
          });
      </script>
	
	
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
                        <br/>			
						<h4>Genre:</h4>
						<ul class="tag-list">
							<g:each var="tag" in="${book?.tags}">
								<li><span>${tag}</span></li>
							</g:each>
						</ul>
						<a id="edit-tags-book-button" href="#">edit</a>
                        <div class="barra">
<%--                            <a href="#" id="votar" class="boton">Votar</a>--%>
                            <a href="#" id="buscar-librerias" class="boton">Buscar Librerias</a>
                            <a href="#" id="buscar-listado" class="boton">Ver Listado</a>
                            <a href="#" id="buscar-mapa" class="boton">Ver Mapa</a>
                        </div>
                    </div>
                </div>
                <div id="mapa">
                      <div id="map_canvas" style="width:100%; height:300px"></div>
                </div>
                <div id="lista-de-librerias">
                    <table>
                    	<g:each var="library" in="${librarys}">
                    		<tr>
	                            <td><b>${library.name}</b></td>
	                            <td>${library.location.street}<span class="icon icon-marker"></span></td>
	                            <td>${library.phone}<span class="icon icon-cell"></span></td>
	                            <td>${library.email}<span class="icon icon-mail"></span></td>
	                        </tr>
                    	</g:each>
                    </table>
                </div>
                <g:if test="${session.user}">
                	<div class="barra">
                		<span id="reservar" class="operation-button">to Reserve</span>
	                    <span id="comentar" class="operation-button">to Comment</span>
	                    
	                </div>
	                <div id="nuevo-comentario">  
	                
	                    <g:form id="new-comment" controller="comment" action="addCommentToBook">
	                        <p>Write here your comment</p>
	                        <g:textArea name="commentText" id="commentText"></g:textArea>
	                        <g:hiddenField name="bookId" value="${book.id}"/>
 
	                        <ul class="star-rating">	                        
	                        	<li class="current-rating" style="width:70%;"></li>
								<li class="star"><a href="#" title='1' class="one-star" onclick=>1</a></li>
								<li class="star"><a href="#" title='2' class="two-stars">2</a></li>
								<li class="star"><a href="#" title='3' class="three-stars">3</a></li>
								<li class="star"><a href="#" title='4' class="four-stars">4</a></li>
								<li class="star"><a href="#" title='5' class="five-stars">5</a></li>
							</ul>
							<input type="hidden" name="bookRating" value="0" id="bookRating" />
	                    
	                     	<g:submitButton name="enviar"></g:submitButton>
	                    </g:form>
	                    
	                </div>
	                <div id="nueva-reserva">  
	                
	                    <g:form id="new-reserve" controller="reservation" action="toReserve">
	                        <p>Select library</p>
	                        <input type="hidden" name="bookId" value="${book.id}" />
	                        <g:select name="libraryId"  optionKey="id" optionValue="name" from="${librarys}" ></g:select>
 							<g:submitButton name="reservar"></g:submitButton>
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
		</body>
</html>