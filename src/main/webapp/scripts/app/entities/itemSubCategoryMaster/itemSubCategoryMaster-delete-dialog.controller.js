'use strict';

angular.module('watererpApp')
	.controller('ItemSubCategoryMasterDeleteController', function($scope, $uibModalInstance, entity, ItemSubCategoryMaster) {

        $scope.itemSubCategoryMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            ItemSubCategoryMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
