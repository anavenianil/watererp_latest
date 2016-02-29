'use strict';

angular.module('watererpApp').controller('WorkflowTypeMasterDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'WorkflowTypeMaster', 'StatusMaster',
        function($scope, $stateParams, $uibModalInstance, entity, WorkflowTypeMaster, StatusMaster) {

        $scope.workflowTypeMaster = entity;
        $scope.statusmasters = StatusMaster.query();
        $scope.load = function(id) {
            WorkflowTypeMaster.get({id : id}, function(result) {
                $scope.workflowTypeMaster = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:workflowTypeMasterUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.workflowTypeMaster.id != null) {
                WorkflowTypeMaster.update($scope.workflowTypeMaster, onSaveSuccess, onSaveError);
            } else {
                WorkflowTypeMaster.save($scope.workflowTypeMaster, onSaveSuccess, onSaveError);
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
