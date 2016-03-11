'use strict';

angular.module('watererpApp').controller('MainSewerageSizeDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'MainSewerageSize',
        function($scope, $stateParams, $uibModalInstance, entity, MainSewerageSize) {

        $scope.mainSewerageSize = entity;
        $scope.load = function(id) {
            MainSewerageSize.get({id : id}, function(result) {
                $scope.mainSewerageSize = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:mainSewerageSizeUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.mainSewerageSize.id != null) {
                MainSewerageSize.update($scope.mainSewerageSize, onSaveSuccess, onSaveError);
            } else {
                MainSewerageSize.save($scope.mainSewerageSize, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
