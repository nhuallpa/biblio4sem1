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