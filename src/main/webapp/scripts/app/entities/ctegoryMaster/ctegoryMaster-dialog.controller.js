'use strict';

angular.module('watererpApp').controller('CtegoryMasterDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'CtegoryMaster',
        function($scope, $stateParams, $uibModalInstance, entity, CtegoryMaster) {

        $scope.ctegoryMaster = entity;
        $scope.load = function(id) {
            CtegoryMaster.get({id : id}, function(result) {
                $scope.ctegoryMaster = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:ctegoryMasterUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.ctegoryMaster.id != null) {
                CtegoryMaster.update($scope.ctegoryMaster, onSaveSuccess, onSaveError);
            } else {
                CtegoryMaster.save($scope.ctegoryMaster, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
