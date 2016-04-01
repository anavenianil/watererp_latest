'use strict';

angular.module('watererpApp')
	.controller('TariffMasterDeleteController', function($scope, $uibModalInstance, entity, TariffMaster) {

        $scope.tariffMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            TariffMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
