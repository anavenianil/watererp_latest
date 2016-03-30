'use strict';

angular.module('watererpApp')
	.controller('ItemCategoryMasterDeleteController', function($scope, $uibModalInstance, entity, ItemCategoryMaster) {

        $scope.itemCategoryMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            ItemCategoryMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
