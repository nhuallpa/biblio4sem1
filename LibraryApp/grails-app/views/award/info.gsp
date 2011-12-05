<%@ page contentType="text/html;charset=ISO-8859-1" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<meta name="layout" content="home"/>
<title>Info : ${award?.detail }</title>
</head>
<body>
  
  	<div id="profile-details">
  		<div class="imagen">
			<img id="award-picture" src="<g:createLinkTo dir="images/award/${award?.category}/" file="award.jpg" />"></img>
  		</div>
  		<div class="resumen-derecho">
			<p id="award-detail">${award?.detail}</p>
			<p class="descripcion">${award?.info}</p>	
		</div>
  	</div>
</body>
</html>