'use strict';

angular.module('watererpApp').controller('CategoryMasterDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'CategoryMaster',
        function($scope, $stateParams, $uibModalInstance, entity, CategoryMaster) {

        $scope.categoryMaster = entity;
        $scope.load = function(id) {
            CategoryMaster.get({id : id}, function(result) {
                $scope.categoryMaster = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:categoryMasterUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.categoryMaster.id != null) {
                CategoryMaster.update($scope.categoryMaster, onSaveSuccess, onSaveError);
            } else {
                CategoryMaster.save($scope.categoryMaster, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
