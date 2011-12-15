<%@ page contentType="text/html;charset=ISO-8859-1" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<meta name="layout" content="home"/>
    <title>Awards</title>
    <g:javascript src="jquery/components/paginator.js"/>
    <g:javascript >
    	    $(document).ready(function(){
                $(function(){ itemsPerPage = 9; $("#resultados").pagination(); });
            });
    </g:javascript>
</head>
<body>
	Awards
	<ul id="resultados" class="columns">
		<g:each in="${awards}" var="award">
			<li class="libro">
			<div class="imagen"><img src="<g:createLinkTo dir="images/award/${award.category}" file="award.jpg"  />" title="Image" width="122px" height="180px"/></div>
				<ul class="detalles">
					<li class="titulo">${award.detail}</li>
					<li class="autor">Scores: ${award.score}</li>
					<li class="info"><a href="${createLink(controller:'award', action:'info', params:[userId:session.user.id, award_id: award.id])}">Info</a></li>					
					<g:if test="${myScore >= award.score}">
						<li class="verDetalles"><a href="${createLink(controller:'award', action:'exchange', params:[userId:session.user.id, awardId: award.id])}">Acquire</a></li>
					</g:if>
					
				</ul>
			</li>
		</g:each>
	</ul>
</body>
</html>