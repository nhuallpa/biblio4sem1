
	/**
	 * Login PopUp
	 */

	$('#login-button').live('click', function(){
		$('#fondo-popup').css('display', 'block');
		$('#login').css('display', 'block'); 
	});

	/**
	 * Registrase PopUp
	 */

	$('#registrarse-button').live('click', function(){
		$('#fondo-popup').css('display', 'block');
		$('#registrarse').css('display', 'block'); 
	});

	/**
	 * Registrase PopUp a PopUp
	 */

	$('#boton-registrarse').live('click', function(){
		$('#login').css('display', 'none'); 
		$('#registrarse').css('display', 'block'); 
	});

	/**
	 * Recuperar PopUp a PopUp
	 */

	$('#boton-recuperar-pass').live('click', function(){
		$('#login').css('display', 'none'); 
		$('#recuperar').css('display', 'block'); 
	});

	/**
	 * Cerrar PopUp
	 */

	$('#boton-cerrar').live('click', function(){
		$('#fondo-popup').css('display', 'none');
		$('#login').css('display', 'none'); 
		$('#registrarse').css('display', 'none'); 
		$('#recuperar').css('display', 'none'); 
	});

	/**
	 * Abrir Comentario
	 */

	$('#comentar').live('click', function(){
		$('#nuevo-comentario').css('display', 'block');
	});

	/**
	 * Enviar comentario
	 */

	$('#enviar').live('click', function(){
		$('#new-comment').submit();
	});

	/**
	 * Responder comentario
	 */

	$('#responder').live('click', function(){
		$('#nuevo-comentario').css('display', 'block');
	});

	/**
	 * buscar-librerias
	 */

	$('#buscar-librerias').live('click', function(){
		$('#mapa').css('display', 'block');
		$('#buscar-listado').css('display', 'block');
		$(this).css('display', 'none');
	});

	/**
	 * buscar-listado
	 */

	$('#buscar-listado').live('click', function(){
		$('#mapa').css('display', 'none');
		$('#buscar-mapa').css('display', 'block');
		$('#buscar-listado').css('display', 'none');
		$('#lista-de-librerias').css('display', 'block');
	});

	/**
	 * buscar-mapa
	 */

	$('#buscar-mapa').live('click', function(){
		$('#mapa').css('display', 'block');
		$('#buscar-mapa').css('display', 'none');
		$('#buscar-listado').css('display', 'block');
		$('#lista-de-librerias').css('display', 'none');
	});