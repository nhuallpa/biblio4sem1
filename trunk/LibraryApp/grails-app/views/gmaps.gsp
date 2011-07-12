<html>
    <head>
        <title>Welcome to GeoTwitter!</title>
        <g:javascript library="application" />
        <g:javascript library="jquery" />
        <meta name="layout" content="main" />

        <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>

        <script type="text/javascript">

        var map;
        var geocoder = new google.maps.Geocoder();

        geocoder.geocode( { address: "Buenos Aires, Sarandi 1082" }, function(results, status) {
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
 
      </script>
    </head>
<body>
  <div id="map_canvas" style="width:400px; height:200px"></div>
</body>
</html>
