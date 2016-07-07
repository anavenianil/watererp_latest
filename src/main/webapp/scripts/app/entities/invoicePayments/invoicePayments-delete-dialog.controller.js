'use strict';

angular.module('watererpApp')
	.controller('InvoicePaymentsDeleteController', function($scope, $uibModalInstance, entity, InvoicePayments) {

        $scope.invoicePayments = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            InvoicePayments.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
