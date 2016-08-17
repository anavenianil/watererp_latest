'use strict';
angular.module('watererpApp')
    .controller('AgeAnalysisReportController', function ($scope, $window, $state, $filter,  $http, 
    		TariffCategoryMaster, DivisionMaster) {

        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.custDetails = {};
        $scope.custDetails.betweenDates = false;
        $scope.message = null;
        $scope.ageAnalysis ={};
        $scope.ageAnalysis.mobile = false;

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
        
        $scope.datePickerForfromdate = {};

		$scope.datePickerForfromdate.status = {
			opened : false
		};

		$scope.datePickerForfromdateOpen = function($event) {
			$scope.datePickerForfromdate.status.opened = true;
		};

	
		 $scope.datePickerFortodate = {};

			$scope.datePickerFortodate.status = {
				opened : false
			};

			$scope.datePickerFortodateOpen = function($event) {
				$scope.datePickerFortodate.status.opened = true;
			};
    });
