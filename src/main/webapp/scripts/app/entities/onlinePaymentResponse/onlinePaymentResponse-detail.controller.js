'use strict';

angular.module('watererpApp')
    .controller('OnlinePaymentResponseDetailController', function ($scope, $rootScope, $stateParams, entity, OnlinePaymentResponse, OnlinePaymentOrder) {
        $scope.onlinePaymentResponse = entity;
        $scope.load = function (id) {
            OnlinePaymentResponse.get({id: id}, function(result) {
                $scope.onlinePaymentResponse = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:onlinePaymentResponseUpdate', function(event, result) {
            $scope.onlinePaymentResponse = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
