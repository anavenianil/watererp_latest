'use strict';

angular.module('watererpApp').controller('CategoryPipeSizeMappingDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'CategoryPipeSizeMapping', 'CategoryMaster', 'PipeSizeMaster',
        function($scope, $stateParams, $uibModalInstance, entity, CategoryPipeSizeMapping, CategoryMaster, PipeSizeMaster) {

        $scope.categoryPipeSizeMapping = entity;
        $scope.categorymasters = CategoryMaster.query();
        $scope.pipesizemasters = PipeSizeMaster.query();
        $scope.load = function(id) {
            CategoryPipeSizeMapping.get({id : id}, function(result) {
                $scope.categoryPipeSizeMapping = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:categoryPipeSizeMappingUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.categoryPipeSizeMapping.id != null) {
                CategoryPipeSizeMapping.update($scope.categoryPipeSizeMapping, onSaveSuccess, onSaveError);
            } else {
                CategoryPipeSizeMapping.save($scope.categoryPipeSizeMapping, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
