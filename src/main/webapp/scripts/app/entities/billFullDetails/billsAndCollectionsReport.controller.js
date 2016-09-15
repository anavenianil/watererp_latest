'use strict';
angular.module('watererpApp')
    .controller('BillsAndCollectionsReportController', function ($scope, $window, $state, $filter,  $http, 
    		     
    		TariffCategoryMaster,CustDetails,CustDetailsSearchCAN, DivisionMaster) {

       $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.custDetails = {};
        $scope.collDetails = {};
        $scope.collDetails.betweenDates = false;
        $scope.message = null;
        $scope.collDetails.betweenDetailedDates =false;

		 //to search CAN
		
		$scope.getLocation = function(val) {
			return $http.get('api/custDetailss/searchCANDetails/' + val, {
				params : {
					address : val,
					sensor : false
				}
			}).then(function(response) {
				var res = response.data.map(function(item) {
					return item;
				});
				return res;
			});
		}



	    $scope.getCustDetails = function(can) {
			CustDetailsSearchCAN.get({can : can}, function(result) {
                $scope.custDetails = result;
            });
        };
        
		$scope.onSelect = function($item, $model, $label) {
			console.log($item);
			var arr = $item.split("-");
			$scope.custDetails = {};
			$scope.custDetails.can = arr[0].trim();
			$scope.custDetails.name = arr[1];
			/*$scope.custDetails.address = arr[2];*/
			$scope.getCustDetails($scope.custDetails.can);
		};

		$scope.refresh = function() {
			$scope.reset();
			$scope.clear();
		};

    });



