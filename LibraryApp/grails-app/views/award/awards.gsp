<%@ page contentType="text/html;charset=ISO-8859-1" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<meta name="layout" content="home"/>
    <title>Books Store</title>
    <g:javascript src="jquery/components/paginator.js"/>
    <g:javascript >
    	    $(document).ready(function(){
                $(function(){ itemsPerPage = 9; $("#resultados").pagination(); });
            });
    </g:javascript>
</head>
<body>
	Premios
	<ul id="resultados" class="columns">
		<g:each in="${awards}" var="award">
			<li class="libro">
				<ul class="detalles">
					<li class="titulo">${award.detail}</li>
					<li class="autor">Puntos: ${award.score}</li>
					<g:if test="${myScore >= award.score}">
						<li class="verDetalles"><a href="${createLink(controller:'award', action:'exchange', params:[userId:session.user.id, subScore: award.score])}">Adquirir</a></li>
					</g:if>
					
				</ul>
			</li>
		</g:each>
	</ul>
</body>
</html>