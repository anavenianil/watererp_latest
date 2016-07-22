'use strict';

angular.module('watererpApp')
    .controller('OnlinePaymentOrderDetailController', function ($scope, $rootScope, $stateParams, entity, OnlinePaymentOrder, MerchantMaster) {
        $scope.onlinePaymentOrder = entity;
        $scope.load = function (id) {
            OnlinePaymentOrder.get({id: id}, function(result) {
                $scope.onlinePaymentOrder = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:onlinePaymentOrderUpdate', function(event, result) {
            $scope.onlinePaymentOrder = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
