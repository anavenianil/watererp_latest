'use strict';

angular.module('watererpApp')
	.controller('TariffChargesDeleteController', function($scope, $uibModalInstance, entity, TariffCharges) {

        $scope.tariffCharges = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            TariffCharges.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
