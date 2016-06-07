'use strict';

angular.module('watererpApp').controller(
		'GetWaterBillDetailsController',
		function($scope, $state, BillFullDetails, ParseLinks, $http,
				CustDetailsSearchCAN) {

			$scope.txnList = [];
			$scope.predicate = 'id';
			$scope.reverse = true;
			$scope.page = 0;

			$scope.reset = function() {
				$scope.page = 0;
				$scope.txnList = [];
			};

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

			$scope.findBillDetails = function(can) {
				$scope.isValidCust = false;
				return $http.get(
						'api/billFullDetailss/getWaterBillDetails/' + can)
						.then(function(response) {
							$scope.txnList = [];
							for (var i = 0; i < response.data.length; i++) {
								$scope.txnList.push(response.data[i]);
							}
						});
			}

			$scope.onSelect = function($item, $model, $label) {
				console.log($item);
				var arr = $item.split("-");
				$scope.custDetails = {};
				$scope.custDetails.can = arr[0].trim();
				$scope.custDetails.name = arr[1];
				$scope.custDetails.address = arr[2];
				$scope.findBillDetails($scope.custDetails.can);
			};

			$scope.refresh = function() {
				$scope.reset();
				$scope.clear();
			};

		});
