'use strict';

angular.module('watererpApp')
    .controller('OnlinePaymentResponseController', function ($scope, $state, OnlinePaymentResponse, ParseLinks) {

        $scope.onlinePaymentResponses = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            OnlinePaymentResponse.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.onlinePaymentResponses.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.onlinePaymentResponses = [];
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
            $scope.onlinePaymentResponse = {
                responseCode: null,
                responseTime: null,
                redirectUrl: null,
                id: null
            };
        };
    });