'use strict';

angular.module('watererpApp')
	.controller('DesignationMasterDeleteController', function($scope, $uibModalInstance, entity, DesignationMaster) {

        $scope.designationMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            DesignationMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
