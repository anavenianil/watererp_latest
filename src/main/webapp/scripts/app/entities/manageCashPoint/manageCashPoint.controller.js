'use strict';

angular.module('watererpApp')
    .controller('ManageCashPointController', function ($scope, $state, ManageCashPoint, ParseLinks) {

        $scope.manageCashPoints = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            ManageCashPoint.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.manageCashPoints.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.manageCashPoints = [];
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
            $scope.manageCashPoint = {
                todayDate: null,
                payeeName: null,
                txnAmount: null,
                openBal: null,
                availBal: null,
                totalReceipts: null,
                locationCode: null,
                id: null
            };
        };
    });
