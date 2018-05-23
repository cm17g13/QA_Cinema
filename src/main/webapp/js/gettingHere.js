function initMap() {
        // var travelMode = document.getElementById('drivingMode').value;
        // console.log(travelMode);
        var directionsService = new google.maps.DirectionsService;
        var directionsDisplay = new google.maps.DirectionsRenderer;
        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 15,
          center: {lat: 53.474061, lng: -2.286404}
        });
        directionsDisplay.setMap(map);

        function myEventHandler(e){
            var keyCode = e.keyCode;
            if(keyCode == 13){
            calculateAndDisplayRoute(directionsService, directionsDisplay);
          }
        };
        document.getElementById('startLocation').addEventListener('keypress', myEventHandler);
      }

      function calculateAndDisplayRoute(directionsService, directionsDisplay) {
        directionsService.route({
          origin: document.getElementById('startLocation').value,
          destination: 'Anchorage, Salford',
          travelMode: 'DRIVING'
        }, function(response, status) {
          if (status === 'OK') {
            directionsDisplay.setDirections(response);
          } else {
            window.alert('Directions request failed due to ' + status);
          }
        });
      }