'use strict';

angular.module('watererpApp')
    .controller('OnlinePaymentCallbackController', function ($scope, $state, OnlinePaymentCallback, ParseLinks) {

        $scope.onlinePaymentCallbacks = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            OnlinePaymentCallback.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.onlinePaymentCallbacks.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.onlinePaymentCallbacks = [];
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
            $scope.onlinePaymentCallback = {
                currency: null,
                paymentMode: null,
                serviceCode: null,
                message: null,
                responseCode: null,
                totalAmountPaid: null,
                userDefinedField: null,
                merchantTxnRef: null,
                id: null
            };
        };
    });
