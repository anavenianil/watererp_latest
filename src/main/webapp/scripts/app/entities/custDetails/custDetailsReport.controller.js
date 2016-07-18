'use strict';

angular.module('watererpApp')
    .controller('CustDetailsReportController', function ($scope, $state, CustDetails, ParseLinks, $http, CustDetailsSearchCAN, 
    		TariffCategoryMaster, DivisionMaster) {

        $scope.custDetailss = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.tariffcategorymasters = TariffCategoryMaster.query();
        $scope.divisionmasters = DivisionMaster.query();
        $scope.categoryId =null;
        
        
        $scope.getCustDetailsByCategory = function(categoryId){
        	$scope.custCategoryCount = [];
			return $http.get('/api/custDetails/getAll',
					categoryId).then(function(response) {
						$scope.custCategoryCount = response.data;
					});
        }
        
        $scope.custDetailsReportDTO = {};
        $scope.getCustDetailsByDma = function(dmaId){
        	$scope.custDetailsReportDTO.dmaId = dmaId;
        	
        	return $http.post('/api/custDetails/getAll',
        			$scope.custDetailsReportDTO).then(
					function(response) {
						$scope.custDetailsReportDTO = response.data;
					});
        }
        
        
        
        /*$scope.getCustDetailsByDma = function(dmaId) {
        	$scope.custDetailsReportDTO.dmaId = dmaId;
			return $http.get('/api/custDetails/getAll',
					$scope.custDetailsReportDTO).then(
					function(response) {
						$scope.custDetails = response.data.custDetails;
					});
		}*/
        
        /*$scope.getCustDetailsByDma = function(dmaId){
        	return $http.get('/api/custDetails/getAll',
        			dmaId).then(function(response) {
				$scope.custCategoryCount = response.data;
			});
        }*/
        
        
        
        
        /*$scope.loadAll = function() {
            CustDetails.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.custDetailss.push(result[i]);
                }
            });
        };*/
        $scope.reset = function() {
            $scope.page = 0;
            $scope.custDetailss = [];
            //$scope.loadAll();
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            //$scope.loadAll();
            //$scope.getCustDetailsByCategory($scope.categoryId);
        };
        //$scope.loadAll();


        $scope.refresh = function () {
            $scope.reset();
        };

        
        
     // to search CAN
		/*$scope.getLocation = function(val) {
			$scope.isValidCust = false;
			return $http.get('api/custDetailss/searchCAN/' + val, {
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
		}*/
		
		// get cust details by CAN
		/*$scope.getCustDetails = function(can) {
			CustDetailsSearchCAN.get({can : can},
							function(result) {
								$scope.custDetails = result;
								$scope.custDetailss.push(result[i]);
							});
		};*/
		
		/*$scope.load = function(id) {
        	$scope.custDetailss=[];
        	CustDetails.get({
				id : id
			}, function(result) {
				$scope.custDetailss.push(result);
			});
		};*/
		
		// when selected searched CAN in DropDown
		/*$scope.onSelect = function($item, $model, $label) {
			console.log($item);
			var arr = $item.split("-");			
			$scope.custDetails = {};
			$scope.custDetails.can = arr[0].trim();
			$scope.custDetails.name = arr[1];
			$scope.custDetails.address = arr[2];
			$scope.custDetails.id = arr[3];
			$state.go('custDetails.detail',{id:$scope.custDetails.id});
			//$scope.load($scope.custDetails.id);
		};*/
		
		/*$scope.custDetailss=[];
		$scope.getCustDetailsByCategory = function(categoryId){
			if($scope.categoryId != categoryId){
				$scope.page = 0;
				$scope.custDetailss=[];
			}
			$scope.categoryId = categoryId;
            CustDetails.query({page: $scope.page, size: 20, categoryId: categoryId}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.custDetailss.push(result[i]);
                }
            });
		}
		$scope.getCustDetailsByCategory($scope.categoryId);*/
    });
