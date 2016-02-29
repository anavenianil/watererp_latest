'use strict';

angular.module('watererpApp').controller('PipeSizeMasterDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'PipeSizeMaster',
        function($scope, $stateParams, $uibModalInstance, entity, PipeSizeMaster) {

        $scope.pipeSizeMaster = entity;
        $scope.load = function(id) {
            PipeSizeMaster.get({id : id}, function(result) {
                $scope.pipeSizeMaster = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:pipeSizeMasterUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.pipeSizeMaster.id != null) {
                PipeSizeMaster.update($scope.pipeSizeMaster, onSaveSuccess, onSaveError);
            } else {
                PipeSizeMaster.save($scope.pipeSizeMaster, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
