function initialize() {
    var latlng = new google.maps.LatLng(-34.682523, -58.554862);
    var settings = {
        zoom: 15,
        center: latlng,
        mapTypeControl: true,
        mapTypeControlOptions: {
            style: google.maps.MapTypeControlStyle.DROPDOWN_MENU
            },
        navigationControl: true,
        navigationControlOptions: {
            style: google.maps.NavigationControlStyle.SMALL
            },
        mapTypeId: google.maps.MapTypeId.ROADMAP
        };
    var map = new google.maps.Map(document.getElementById("map_canvas"), settings);
    /*	var contentString = '<div id="content">'+
					/*'<div id="siteNotice">'+
					'</div>'+
					'<h2>America 2333</h2>'+
					/*'<div id="bodyContent">'+
					'<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>'+
					'</div>'+
					'</div>';
				var infowindow = new google.maps.InfoWindow({
					content: contentString
				});*/
				
    var companyImage = new google.maps.MarkerImage('../../js/googlemaps/images/logo_mapa.png',
        new google.maps.Size(57,83),
        new google.maps.Point(0,0),
        new google.maps.Point(10,110)
        );

    var companyShadow = new google.maps.MarkerImage('../../js/googlemaps/images/logo_shadow.png',
        new google.maps.Size(130,50),
        new google.maps.Point(0,0),
        new google.maps.Point(40, 70));

    var companyPos = new google.maps.LatLng(-34.682523, -58.554862);

    var companyMarker = new google.maps.Marker({
        position: companyPos,
        map: map,
        icon: companyImage,
        shadow: companyShadow,
        title:"Paraguay 2487",
        zIndex: 3
    });
				
    /*var trainImage = new google.maps.MarkerImage('images/train.png',
					new google.maps.Size(50,50),
					new google.maps.Point(0,0),
					new google.maps.Point(50,50)
				);

				var trainShadow = new google.maps.MarkerImage('images/train_shadow.png',
					new google.maps.Size(70,50),
					new google.maps.Point(0,0),
					new google.maps.Point(60, 50)
				);

				var trainPos = new google.maps.LatLng(-34.6631022,  -58.5782513);

				var trainMarker = new google.maps.Marker({
					position: trainPos,
					map: map,
					icon: trainImage,
					shadow: trainShadow,
					title:"Train Station",
					zIndex: 2
				});

				var parkingImage = new google.maps.MarkerImage('images/parking.png',
					new google.maps.Size(50,50),
					new google.maps.Point(0,0),
					new google.maps.Point(50,50)
				);

				var parkingShadow = new google.maps.MarkerImage('images/parking_shadow.png',
					new google.maps.Size(70,50),
					new google.maps.Point(0,0),
					new google.maps.Point(60, 50)
				);

				var parkingPos = new google.maps.LatLng(-34.6631022,  -58.5782513);

				var parkingMarker = new google.maps.Marker({
					position: parkingPos,
					map: map,
					icon: parkingImage,
					shadow: parkingShadow,
					title:"Parking Lot",
					zIndex: 1
				});*/
				
    google.maps.event.addListener(companyMarker, 'click', function() {
        infowindow.open(map,companyMarker);
    });
}