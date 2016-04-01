'use strict';

angular.module('watererpApp')
	.controller('BillOfMaterialDeleteController', function($scope, $uibModalInstance, entity, BillOfMaterial) {

        $scope.billOfMaterial = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            BillOfMaterial.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
