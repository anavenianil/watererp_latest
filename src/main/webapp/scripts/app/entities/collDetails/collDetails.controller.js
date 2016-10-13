'use strict';

angular.module('watererpApp')
    .controller('CollDetailsController', function ($scope, $state, CollDetails, ParseLinks, $http) {

        $scope.collDetailss = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            CollDetails.query({page: $scope.page, size: 20, txnStatus : 'C', sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.collDetailss.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.collDetailss = [];
            $scope.loadAll();
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.reset();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.collDetails = {
                reversalRef: null,
                receiptNo: null,
                receiptAmt: null,
                receiptDt: null,
                receiptMode: null,
                instrNo: null,
                instrDt: null,
                instrIssuer: null,
                svrStatus: null,
                can: null,
                consName: null,
                terminalId: null,
                collTime: null,
                txnStatus: null,
                meterReaderId: null,
                userId: null,
                remarks: null,
                settlementId: null,
                extSettlementId: null,
                lat: null,
                longI: null,
                id: null
            };
        };
        
     // to search CAN
		$scope.getLocation = function(val) {
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
		}
		
		
		$scope.onSelect = function($item, $model, $label) {
			console.log($item);
			var arr = $item.split("-");			
			$scope.can = arr[0].trim();
			$scope.collDetailss = CollDetails.getCollDetailsByCan({can : $scope.can});
		};
    });
