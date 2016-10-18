'use strict';

angular.module('watererpApp')
    .controller('SewerageChargeMasterController', function ($scope, $state, SewerageChargeMaster, ParseLinks) {

        $scope.sewerageChargeMasters = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 1;
        $scope.loadAll = function() {
            SewerageChargeMaster.query({page: $scope.page - 1, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.totalItems = headers('X-Total-Count');
                $scope.sewerageChargeMasters = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.sewerageChargeMaster = {
                amount: null,
                id: null
            };
        };
    });
