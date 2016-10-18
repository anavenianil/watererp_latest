'use strict';

angular.module('watererpApp')
    .controller('SewerageRequestController', function ($scope, $state, SewerageRequest, ParseLinks) {

        $scope.sewerageRequests = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            SewerageRequest.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.sewerageRequests.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.sewerageRequests = [];
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
            $scope.sewerageRequest = {
                customerName: null,
                mobileNo: null,
                requestedDate: null,
                receiptNo: null,
                address: null,
                amount: null,
                paymentDate: null,
                id: null
            };
        };
    });
