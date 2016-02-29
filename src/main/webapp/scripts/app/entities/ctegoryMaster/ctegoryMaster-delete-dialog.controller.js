'use strict';

angular.module('watererpApp')
	.controller('CtegoryMasterDeleteController', function($scope, $uibModalInstance, entity, CtegoryMaster) {

        $scope.ctegoryMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            CtegoryMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
