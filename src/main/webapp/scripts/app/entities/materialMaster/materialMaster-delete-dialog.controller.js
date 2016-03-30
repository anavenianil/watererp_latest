'use strict';

angular.module('watererpApp')
	.controller('MaterialMasterDeleteController', function($scope, $uibModalInstance, entity, MaterialMaster) {

        $scope.materialMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            MaterialMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
