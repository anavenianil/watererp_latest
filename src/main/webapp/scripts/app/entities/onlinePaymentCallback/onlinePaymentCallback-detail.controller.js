'use strict';

angular.module('watererpApp')
    .controller('OnlinePaymentCallbackDetailController', function ($scope, $rootScope, $stateParams, entity, OnlinePaymentCallback, MerchantMaster, OnlinePaymentOrder) {
        $scope.onlinePaymentCallback = entity;
        $scope.load = function (id) {
            OnlinePaymentCallback.get({id: id}, function(result) {
                $scope.onlinePaymentCallback = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:onlinePaymentCallbackUpdate', function(event, result) {
            $scope.onlinePaymentCallback = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
