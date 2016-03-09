'use strict';

angular.module('watererpApp').controller('ModuleDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Module',
        function($scope, $stateParams, $uibModalInstance, entity, Module) {

        $scope.module = entity;
        $scope.load = function(id) {
            Module.get({id : id}, function(result) {
                $scope.module = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:moduleUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.module.id != null) {
                Module.update($scope.module, onSaveSuccess, onSaveError);
            } else {
                Module.save($scope.module, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForModifiedDate = {};

        $scope.datePickerForModifiedDate.status = {
            opened: false
        };

        $scope.datePickerForModifiedDateOpen = function($event) {
            $scope.datePickerForModifiedDate.status.opened = true;
        };
}]);
