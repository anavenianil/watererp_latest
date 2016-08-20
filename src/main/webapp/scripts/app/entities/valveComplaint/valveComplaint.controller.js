'use strict';

angular.module('watererpApp')
    .controller('ValveComplaintController', function ($scope, $state, ValveComplaint, ParseLinks) {

        $scope.valveComplaints = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            ValveComplaint.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.valveComplaints.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.valveComplaints = [];
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
            $scope.valveComplaint = {
                closedTime: null,
                openTime: null,
                colourCode: null,
                side: null,
                noOfTurns: null,
                valveSize: null,
                valveNo: null,
                repairCode: null,
                distanceLeft: null,
                distanceSb: null,
                distanceZ: null,
                id: null
            };
        };
    });
