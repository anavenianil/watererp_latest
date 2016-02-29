'use strict';

angular.module('watererpApp')
	.controller('CategoryPipeSizeMappingDeleteController', function($scope, $uibModalInstance, entity, CategoryPipeSizeMapping) {

        $scope.categoryPipeSizeMapping = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            CategoryPipeSizeMapping.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
