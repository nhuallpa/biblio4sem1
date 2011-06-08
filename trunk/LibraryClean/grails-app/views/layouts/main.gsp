<!DOCTYPE html>
<html>
    <head>
        <title><g:layoutTitle default="Library -Home" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'library.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:layoutHead />
        <g:javascript library="application" />
    </head>
    <body>
        <div id="header">
        	<div id="libraryLogo">
        		<img src="${resource(dir:'images',file:'Library/LogoLibrary.gif')}" alt="Library" border="0" />
        	</div>
        	<h1>Library</h1>	
        </div>
        <div id="content">
        	<g:layoutBody />
        </div>
        <div id="footer">
        	<span>Los mejores Libros</span>
        </div>
    </body>
</html>