'use strict';

angular.module('watererpApp')
    .controller('ApprovalDetailsController', function ($scope, $state, ApprovalDetails, ParseLinks) {

        $scope.approvalDetailss = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            ApprovalDetails.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.approvalDetailss.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.approvalDetailss = [];
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
            $scope.approvalDetails = {
                remarks: null,
                approvedDate: null,
                approvedEmpNo: null,
                approvedEmpName: null,
                approvedEmpDesig: null,
                id: null
            };
        };
    });
