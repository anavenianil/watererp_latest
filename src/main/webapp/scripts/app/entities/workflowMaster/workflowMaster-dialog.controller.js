'use strict';

angular.module('watererpApp').controller('WorkflowMasterDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'WorkflowMaster', 'StatusMaster',
        function($scope, $stateParams, $uibModalInstance, entity, WorkflowMaster, StatusMaster) {

        $scope.workflowMaster = entity;
        $scope.statusmasters = StatusMaster.query();
        $scope.load = function(id) {
            WorkflowMaster.get({id : id}, function(result) {
                $scope.workflowMaster = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:workflowMasterUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.workflowMaster.id != null) {
                WorkflowMaster.update($scope.workflowMaster, onSaveSuccess, onSaveError);
            } else {
                WorkflowMaster.save($scope.workflowMaster, onSaveSuccess, onSaveError);
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
