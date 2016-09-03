'use strict';
angular.module('watererpApp')
    .controller('BillsAndCollectionsReportController', function ($scope, $window, $state, $filter,  $http, 
    		     
    		TariffCategoryMaster, DivisionMaster) {

        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.custDetails = {};
        $scope.collDetails = {};
        $scope.collDetails.betweenDates = false;
        $scope.message = null;
        $scope.collDetails.betweenDetailedDates =false;
        
        
        $scope.onSelect = function($item, $model, $label) {
			console.log($item);
			var arr = $item.split("-");
			$scope.custDetails = {};
			$scope.custDetails.can = arr[0].trim();
			$scope.custDetails.name = arr[1];
			$scope.custDetails.address = arr[2];
			$scope.getCustDetails($scope.custDetails.can);
			$scope.custInfo = ""; 
			$scope.isValidCust = true;
		};
        
        
        
        
	
	/*	 $scope.getReport = function () {
		     	if($scope.custInfo != null ){
		     	var cust = $scope.custDetails.divisionMaster.id;
		             	

		         $scope.collDetails = {};
		         
		         	$window.open('/api/Revenue/reports/' + divisionId + '/' + tariffCategoryId + '/' + formatDate(dateOfYear) , "_blank")
		             location.reload();
		         }
		       
		         
		         
		     };*/
    });



