var app = angular.module('qaCinema', []);
app.controller('qaController', function($scope, $http) {
	$scope.myInterval = 3000;
	$scope.stitle = null;
	$scope.syear = null;
	$scope.stime = null;
	$scope.sscreenNo = null;

	$scope.loadMovie = function () {
		$scope.movieTitle = localStorage.getItem("stored_title");
		let omdbCode = localStorage.getItem("stored_code");
		$http({                    method: "GET",
                    url: "http://omdbapi.com/?apikey=dbd34baf&i="+omdbCode
                }).then(function mySuccess (response) {

                	$scope.moviePoster = response.data.Poster;
                	$scope.movieDescription = response.data.Plot;
                	$scope.movieActors = response.data.Actors;
                	$scope.movieDirector = response.data.Director;
                	$scope.movieGenre = response.data.Genre;
                	$scope.movieRelease = response.data.Released;
                	$scope.movieIMDBR = response.data.imdbRating;
                	$scope.movieTime = response.data.Runtime;

                }, function myError(response) {
                       
                });
	};

	// $scope.firstName = null;
	// $scope.secondName = null;
	// $scope.accountNumber = null;
	// $scope.id = null;
	// $scope.updatefName=null;
	// $scope.updatelName=null;
	// $scope.updateANumber=null;
	// $scope.lblMsg = null;

	$scope.getAll = function(){
		$http({
	        method : "GET",
	        url : "http://localhost:8080/CinemaApp/rest/cinema/showing"
	    }).then(function mySuccess(reply) {
	        $scope.myShowings = reply.data;
	    }, function myError(reply) {
	        $scope.myShowings = reply.statusText;
	    });	
	};

	$scope.deletedata = function (idToDelete) {
		var url = "http://localhost:8080/CinemaApp/rest/cinema/showing/" + idToDelete.toString();
		$http.delete(url).then(function (reply) {
			if (reply.data)
			$scope.msg = "Showing deleted!";
		}, function (reply) {
			$scope.msg = "Cannot delete the showing.";
			$scope.statusval = reply.status;
			$scope.statustext = reply.statusText;
			$scope.headers = reply.headers();
		});
	};

    $scope.postdata = function (OMDbCode, title, year, screenNo, time, standardSeats, disabledSeats, imageURL){
    	var data = {
    		OMDbCode: OMDbCode,
			stitle: title,
			syear: year,
			sscreenNo: screenNo,
			stime : time,
			standardSeats : standardSeats,
			disabledSeats : disabledSeats

    	};
		$http.post('http://localhost:8080/CinemaApp/rest/cinema/showing', JSON.stringify(data)).then(function (reply) {
			if (reply.data)
			$scope.msg = "Showing created!";
		}, function (reply) {
			$scope.msg = "Service not available";
			$scope.statusval = reply.status;
			$scope.statustext = reply.statusText;
			$scope.headers = reply.headers();
		});
	};

	$scope.pass = function (pass_code, pass_title, pass_year) {
		window.localStorage.setItem("stored_code", pass_code);
		window.localStorage.setItem("stored_title", pass_title);
		window.localStorage.setItem("stored_year", pass_year);
	}
});
   
		