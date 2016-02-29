'use strict';

angular.module('watererpApp')
    .controller('PaymentTypesDetailController', function ($scope, $rootScope, $stateParams, entity, PaymentTypes) {
        $scope.paymentTypes = entity;
        $scope.load = function (id) {
            PaymentTypes.get({id: id}, function(result) {
                $scope.paymentTypes = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:paymentTypesUpdate', function(event, result) {
            $scope.paymentTypes = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
