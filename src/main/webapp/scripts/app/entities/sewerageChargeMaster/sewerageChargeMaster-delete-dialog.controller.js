'use strict';

angular.module('watererpApp')
	.controller('SewerageChargeMasterDeleteController', function($scope, $uibModalInstance, entity, SewerageChargeMaster) {

        $scope.sewerageChargeMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            SewerageChargeMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
