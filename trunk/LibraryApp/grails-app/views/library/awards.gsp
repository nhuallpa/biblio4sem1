<%@ page contentType="text/html;charset=ISO-8859-1" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<meta name="layout" content="main"/>
<title>Awards List</title>
</head>
<body>
  <div class="body">
  					<g:each var="award" in="${awards}">
						<li class="book">
						  <div class="resumen-derecho">
								<p>Detail: ${award.detail}</p><br>
								<p>Score: ${award.score}</p>
						  </div>
					    </li>
					</g:each>
  </div>
</body>
</html>