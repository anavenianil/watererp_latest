'use strict';

angular.module('watererpApp')
    .controller('HydrantComplaintController', function ($scope, $state, HydrantComplaint, ParseLinks) {

        $scope.hydrantComplaints = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            HydrantComplaint.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.hydrantComplaints.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.hydrantComplaints = [];
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
            $scope.hydrantComplaint = {
                fromLeft: null,
                fromSb: null,
                problemAt: null,
                activityType: null,
                pressure: null,
                pressureTime: null,
                flow: null,
                flowTime: null,
                id: null
            };
        };
    });
