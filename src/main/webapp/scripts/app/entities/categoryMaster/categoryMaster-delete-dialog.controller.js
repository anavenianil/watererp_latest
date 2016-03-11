'use strict';

angular.module('watererpApp')
	.controller('CategoryMasterDeleteController', function($scope, $uibModalInstance, entity, CategoryMaster) {

        $scope.categoryMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            CategoryMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
