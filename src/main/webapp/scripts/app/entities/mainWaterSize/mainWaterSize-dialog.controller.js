'use strict';

angular.module('watererpApp').controller('MainWaterSizeDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'MainWaterSize',
        function($scope, $stateParams, $uibModalInstance, entity, MainWaterSize) {

        $scope.mainWaterSize = entity;
        $scope.load = function(id) {
            MainWaterSize.get({id : id}, function(result) {
                $scope.mainWaterSize = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:mainWaterSizeUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.mainWaterSize.id != null) {
                MainWaterSize.update($scope.mainWaterSize, onSaveSuccess, onSaveError);
            } else {
                MainWaterSize.save($scope.mainWaterSize, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
