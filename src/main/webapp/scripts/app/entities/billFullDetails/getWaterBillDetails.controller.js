'use strict';

angular.module('watererpApp')
    .controller('GetWaterBillDetailsController', function ($scope, $state, BillFullDetails, ParseLinks, $http, CustDetailsSearchCAN, 
    		BillFullDetailsBillMonths, CollDetails) {

        $scope.billFullDetailss = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
       
        $scope.loadAll = function() {
            BillFullDetails.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.billFullDetailss.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            //$scope.billFullDetailss = [];
            //$scope.loadAll();
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            //$scope.loadAll();
        };
        //$scope.loadAll();
        
        $scope.getLocation = function(val) {
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
		}
        
        /*$scope.getCustDetails = function(can) {
        	BillFullDetailsBillMonths.findByCan({can : can},function(result) {
								$scope.getWaterDetails = result;
							});
		};*/
		
        $scope.getWaterBills = function(can) {
        	$scope.billFullDetailss = [];
			BillFullDetails.query({
				page : 0,
				size : 20,
				can : can
			}, function(result, headers) {
				$scope.links = ParseLinks.parse(headers('link'));
				for (var i = 0; i < result.length; i++) {
					$scope.billFullDetailss.push(result[i]);
				}
			});
		};
		
		
		$scope.getCollection = function(can) {
        	$scope.collDetailss = [];
			CollDetails.query({
				page : 0,
				size : 20,
				can : can
			}, function(result, headers) {
				$scope.links = ParseLinks.parse(headers('link'));
				for (var i = 0; i < result.length; i++) {
					$scope.collDetailss.push(result[i]);
				}
			});
		};
		$scope.billingLists = [];
		 $scope.findBillDetails = function(can) {
				$scope.isValidCust = false;
				return $http.get('api/billFullDetailss/searchCAN/'+ can, {
					params : { 
						address : can,
						sensor : false
					}
				}).then(function(response) {
					$scope.billingLists = [];
					$scope.creditDTOs = [];
					$scope.debitDTOs = [];
					for (var i = 0; i < response.data.length; i++) {
						$scope.billingLists.push(response.data[i]);
						$scope.creditDTOs.push($scope.billingLists.creditDTO);
						$scope.debitDTOs.push($scope.billingLists.debitDTO);
					}
					//$scope.billingLists = response.data;
				});
			}
		 
        $scope.onSelect = function($item, $model, $label) {
			console.log($item);
			var arr = $item.split("-");
			$scope.custDetails = {};
			$scope.custDetails.can = arr[0].trim();
			$scope.custDetails.name = arr[1];
			$scope.custDetails.address = arr[2];
			//$scope.getWaterBills($scope.custDetails.can);
			//$scope.getCollection($scope.custDetails.can);
			$scope.findBillDetails($scope.custDetails.can);
		};


        $scope.refresh = function () {
            $scope.reset();
            $scope.clear();
        };

        
       
    });
