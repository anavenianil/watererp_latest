'use strict';

angular.module('watererpApp')
	.controller('ItemCodeMasterDeleteController', function($scope, $uibModalInstance, entity, ItemCodeMaster) {

        $scope.itemCodeMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            ItemCodeMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
