'use strict';

angular.module('watererpApp')
    .controller('ItemRequiredController', function ($scope, $state, ItemRequired, ParseLinks, $stateParams) {

        $scope.itemRequireds = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        
        if($stateParams.applicationTxnId != null){
            ItemRequired.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id'], 
            	applicationTxnId : $stateParams.applicationTxnId}, function(result, headers) {
            		$scope.links = ParseLinks.parse(headers('link'));
            		for (var i = 0; i < result.length; i++) {
            			$scope.itemRequireds.push(result[i]);
            		}
            	});
        }
        
        
        
        $scope.loadAll = function() {
            ItemRequired.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.itemRequireds.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.itemRequireds = [];
            //$scope.loadAll();
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            //$scope.loadAll();
        };
        //$scope.loadAll();


        $scope.refresh = function () {
            $scope.reset();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.itemRequired = {
                description: null,
                unit: null,
                quantity: null,
                ratePerShs: null,
                amount: null,
                id: null
            };
        };
    });
