'use strict';

angular.module('watererpApp')
    .controller('BillRunMasterController', function ($scope, $state, BillRunMaster, ParseLinks) {

        $scope.billRunMasters = [];
        $scope.predicate = 'id';
        $scope.reverse = false;
        $scope.page = 0;
        $scope.loadAll = function() {
            BillRunMaster.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.billRunMasters.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.billRunMasters = [];
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
            $scope.billRunMaster = {
                date: null,
                area: null,
                success: null,
                failed: null,
                status: null,
                id: null
            };
        };
    });
