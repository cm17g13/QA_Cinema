var app = angular.module('qaCinema', []);
app.controller('qacController', function($scope, $http) {
	$scope.stitle = null;
	$scope.syear = null;
	$scope.stime = null;
	$scope.sscreenNo = null;

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
			$scope.msg = "Service does not exist";
			$scope.statusval = reply.status;
			$scope.statustext = reply.statusText;
			$scope.headers = reply.headers();
		});
	};

    $scope.postdata = function (OMDBCode, title, year, screenNo, time, standardSeats, disabledSeats, imageURL){
    	var data = {
    		OMDBCode: OMDBCode,
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
});
   
		