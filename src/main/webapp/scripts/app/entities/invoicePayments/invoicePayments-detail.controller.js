'use strict';

angular.module('watererpApp')
    .controller('InvoicePaymentsDetailController', function ($scope, $rootScope, $stateParams, entity, InvoicePayments, CustDetails, BillFullDetails, CollDetails) {
        $scope.invoicePayments = entity;
        $scope.load = function (id) {
            InvoicePayments.get({id: id}, function(result) {
                $scope.invoicePayments = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:invoicePaymentsUpdate', function(event, result) {
            $scope.invoicePayments = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
