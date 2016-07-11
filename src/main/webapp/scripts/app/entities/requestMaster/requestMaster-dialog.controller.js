'use strict';

angular.module('watererpApp').controller('RequestMasterDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'RequestMaster', 'StatusMaster', 'Module',
        function($scope, $stateParams, $uibModalInstance, entity, RequestMaster, StatusMaster, Module) {

        $scope.requestMaster = entity;
        $scope.statusmasters = StatusMaster.query();
        $scope.modules = Module.query();
        $scope.load = function(id) {
            RequestMaster.get({id : id}, function(result) {
                $scope.requestMaster = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:requestMasterUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.requestMaster.id != null) {
                RequestMaster.update($scope.requestMaster, onSaveSuccess, onSaveError);
            } else {
                RequestMaster.save($scope.requestMaster, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForCreationDate = {};

        $scope.datePickerForCreationDate.status = {
            opened: false
        };

        $scope.datePickerForCreationDateOpen = function($event) {
            $scope.datePickerForCreationDate.status.opened = true;
        };
        $scope.datePickerForLastModifiedDate = {};

        $scope.datePickerForLastModifiedDate.status = {
            opened: false
        };

        $scope.datePickerForLastModifiedDateOpen = function($event) {
            $scope.datePickerForLastModifiedDate.status.opened = true;
        };
}]);
