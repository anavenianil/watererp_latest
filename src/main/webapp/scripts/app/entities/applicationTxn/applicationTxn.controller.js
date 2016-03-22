'use strict';

angular.module('watererpApp')
    .controller('ApplicationTxnController', function ($scope, $state, ApplicationTxn, ParseLinks) {

        $scope.applicationTxns = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            ApplicationTxn.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.applicationTxns.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.applicationTxns = [];
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
            $scope.applicationTxn = {
                sHouseNo: null,
                govtOfficialNo: null,
                ward: null,
                street: null,
                pincode: null,
                block: null,
                area: null,
                section: null,
                constituency: null,
                email: null,
                telephoneNumber: null,
                mobile: null,
                scanPlan: null,
                scanPlan1: null,
                saleDeed: null,
                saleDeed1: null,
                totalPlinthArea: null,
                createdDate: null,
                updatedDate: null,
                status: null,
                fileNumber: null,
                id: null
            };
        };
        
        $scope.onSearch = function(status) {
        	$scope.applicationTxns = [];
        	//$('#viewApplicationTxnModal').modal('show');
            ApplicationTxn.query({page: $scope.page, size: 20, status: status, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.applicationTxns.push(result[i]);
                }
            });
        };
    });
