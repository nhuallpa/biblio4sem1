// Login Form

$(function() {
    var button = $('#loginButton');
    var box = $('#loginBox');
    var form = $('.loginForm');
    button.removeAttr('href');
    button.mouseup(function(login) {
        box.toggle();
        button.toggleClass('active');
    });
    form.mouseup(function() { 
        return false;
    });
    $(this).mouseup(function(login) {
        if(!($(login.target).parent('#loginButton').length > 0)) {
            button.removeClass('active');
            box.hide();
        }
    });
	
    /**
	 * Login PopUp
	 */

	$('#login-button').live('click', function(){
		$('#fondo-popup').css('display', 'block');
		$('#login').css('display', 'block'); 
	});

	// reutilizar para Book y Profile
	$('#edit-tags-profile-button').live('click', function(){
		$('#fondo-popup').css('display', 'block');
		$('#edit-tags-profile').css('display','block');
	});
	
	$('#edit-tags-book-button').live('click', function(){
		$('#fondo-popup').css('display', 'block');
		$('#edit-tags-book').css('display','block');
	});
	/**
	 * Registrase PopUp
	 */

//	$('#registrarse-button').live('click', function(){
//		$('#fondo-popup').css('display', 'block');
//		$('#registrarse').css('display', 'block'); 
//	});

	/**
	 * Registrase PopUp a PopUp
	 */

//	$('#boton-registrarse').live('click', function(){
//		$('#login').css('display', 'none'); 
//		$('#registrarse').css('display', 'block'); 
//	});

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
		$('#edit-tags-profile').css('display', 'none');
		$('#edit-tags-book').css('display', 'none'); 
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
	 * Abrir a Reservar
	 */

	$('#reservar').live('click', function(){
		$('#nueva-reserva').css('display', 'block');
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
	
});


// google map: Ariel

var map;
var geocoder = new google.maps.Geocoder();

geocoder.geocode( { address: "${session.user.seeAddress()}" }, function(results, status) {
    if (status == google.maps.GeocoderStatus.OK && results.length) {

      if (status != google.maps.GeocoderStatus.ZERO_RESULTS) {
        var latlng = results[0].geometry.location;

        var myOptions = {
          zoom: 12,
          center: latlng,
          mapTypeId: google.maps.MapTypeId.ROADMAP
        }

        map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
        //map.set_center(results[0].geometry.location);
        var marker = new google.maps.Marker({
            position: latlng,
            map: map
        });
      }
    } else {
      alert("Geocode was unsuccessful due to: " + status);
    }
  });
  