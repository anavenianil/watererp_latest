'use strict';

angular.module('watererpApp')
	.controller('TariffCategoryMasterDeleteController', function($scope, $uibModalInstance, entity, TariffCategoryMaster) {

        $scope.tariffCategoryMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            TariffCategoryMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
