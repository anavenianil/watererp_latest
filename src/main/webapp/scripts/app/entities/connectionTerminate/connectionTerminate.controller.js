'use strict';

angular.module('watererpApp')
    .controller('ConnectionTerminateController', function ($scope, $state, ConnectionTerminate, ParseLinks) {

        $scope.connectionTerminates = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            ConnectionTerminate.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.connectionTerminates.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.connectionTerminates = [];
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
            $scope.connectionTerminate = {
                can: null,
                requestDate: null,
                meterRecovered: null,
                lastMeterReading: null,
                meterRecoveredDate: null,
                id: null
            };
        };
    });
