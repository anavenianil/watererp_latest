'use strict';

angular.module('watererpApp')
    .controller('ReceiptDetailController', function ($scope, $rootScope, $stateParams, entity, Receipt, ApplicationTxn, PaymentTypes) {
        $scope.receipt = entity;
        $scope.load = function (id) {
            Receipt.get({id: id}, function(result) {
                $scope.receipt = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:receiptUpdate', function(event, result) {
            $scope.receipt = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
