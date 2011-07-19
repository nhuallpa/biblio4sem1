<html>
    <head>
        <title>Librerias afiliadas</title>
        <g:javascript library="application" />
        <g:javascript library="jquery" />
        <meta name="layout" content="main" />

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

        		<g:each in="${libraryList}" var="library">
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
  <div id="map_canvas" style="width:800px; height:480px"></div>
</body>
</html>
