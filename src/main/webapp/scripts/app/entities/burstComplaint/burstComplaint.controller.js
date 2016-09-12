'use strict';

angular.module('watererpApp')
    .controller('BurstComplaintController', function ($scope, $state, BurstComplaint, ParseLinks) {

        $scope.burstComplaints = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            BurstComplaint.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.burstComplaints.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.burstComplaints = [];
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
            $scope.burstComplaint = {
                meterLocation: null,
                burstPipe: null,
                fromLeft: null,
                fromSb: null,
                burstArea: null,
                pipeType: null,
                pipeSize: null,
                holeSize: null,
                repairCode: null,
                id: null
            };
        };
    });
