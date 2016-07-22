'use strict';

angular.module('watererpApp')
    .controller('OnlinePaymentOrderController', function ($scope, $state, OnlinePaymentOrder, ParseLinks) {

        $scope.onlinePaymentOrders = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.loadAll = function() {
            OnlinePaymentOrder.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.onlinePaymentOrders.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.onlinePaymentOrders = [];
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
            $scope.onlinePaymentOrder = {
                serviceCode: null,
                amount: null,
                payBy: null,
                userDefinedField: null,
                email: null,
                phone: null,
                orderTime: null,
                id: null
            };
        };
    });
