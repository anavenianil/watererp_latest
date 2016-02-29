'use strict';

angular.module('watererpApp')
	.controller('PipeSizeMasterDeleteController', function($scope, $uibModalInstance, entity, PipeSizeMaster) {

        $scope.pipeSizeMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            PipeSizeMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
