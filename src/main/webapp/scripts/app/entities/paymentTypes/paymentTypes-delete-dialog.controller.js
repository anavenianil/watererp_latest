'use strict';

angular.module('watererpApp')
	.controller('PaymentTypesDeleteController', function($scope, $uibModalInstance, entity, PaymentTypes) {

        $scope.paymentTypes = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            PaymentTypes.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
