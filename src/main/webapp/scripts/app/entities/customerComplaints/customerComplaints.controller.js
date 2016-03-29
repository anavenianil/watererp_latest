'use strict';

angular.module('watererpApp')
    .controller('CustomerComplaintsController', function ($scope, $state, CustomerComplaints, ParseLinks) {

        $scope.customerComplaintss = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            CustomerComplaints.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.customerComplaintss.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.customerComplaintss = [];
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
            $scope.customerComplaints = {
                remarks: null,
                relevantDoc: null,
                complaintBy: null,
                complaintDate: null,
                id: null
            };
        };
    });
