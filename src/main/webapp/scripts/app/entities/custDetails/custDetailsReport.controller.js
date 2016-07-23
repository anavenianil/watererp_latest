'use strict';
angular.module('watererpApp')
    .controller('CustDetailsReportController', function ($scope, $state, $http, 
    		TariffCategoryMaster, DivisionMaster) {


        $scope.custDetailss = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;

        
     	
        $scope.getDivisionMasters = function() {
        	$scope.divisionmasters = [];
			return $http.get('/api/divisionMasters/getAll').then(function(response) {
						$scope.divisionmasters = response.data;
					});
		}
        $scope.getDivisionMasters();
        
        $scope.getTariffCategoryMasters = function() {
        	$scope.tariffcategorymasters = [];
			return $http.get('/api/tariffCategoryMasters/getAll').then(function(response) {
						$scope.tariffcategorymasters = response.data;
					});
		}
        $scope.getTariffCategoryMasters();
              

    });
