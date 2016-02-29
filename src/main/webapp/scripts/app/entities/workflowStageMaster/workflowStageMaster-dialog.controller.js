'use strict';

angular.module('watererpApp').controller('WorkflowStageMasterDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'WorkflowStageMaster', 'StatusMaster',
        function($scope, $stateParams, $uibModalInstance, entity, WorkflowStageMaster, StatusMaster) {

        $scope.workflowStageMaster = entity;
        $scope.statusmasters = StatusMaster.query();
        $scope.load = function(id) {
            WorkflowStageMaster.get({id : id}, function(result) {
                $scope.workflowStageMaster = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:workflowStageMasterUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.workflowStageMaster.id != null) {
                WorkflowStageMaster.update($scope.workflowStageMaster, onSaveSuccess, onSaveError);
            } else {
                WorkflowStageMaster.save($scope.workflowStageMaster, onSaveSuccess, onSaveError);
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
