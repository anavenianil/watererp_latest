'use strict';

angular.module('watererpApp').controller('RequestWorkflowHistoryDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'RequestWorkflowHistory', 'User', 'StatusMaster', 'RequestMaster', 'WorkflowMaster', 'WorkflowStageMaster',
        function($scope, $stateParams, $uibModalInstance, entity, RequestWorkflowHistory, User, StatusMaster, RequestMaster, WorkflowMaster, WorkflowStageMaster) {

        $scope.requestWorkflowHistory = entity;
        $scope.users = User.query();
        $scope.statusmasters = StatusMaster.query();
        $scope.requestmasters = RequestMaster.query();
        $scope.workflowmasters = WorkflowMaster.query();
        $scope.workflowstagemasters = WorkflowStageMaster.query();
        $scope.load = function(id) {
            RequestWorkflowHistory.get({id : id}, function(result) {
                $scope.requestWorkflowHistory = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:requestWorkflowHistoryUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.requestWorkflowHistory.id != null) {
                RequestWorkflowHistory.update($scope.requestWorkflowHistory, onSaveSuccess, onSaveError);
            } else {
                RequestWorkflowHistory.save($scope.requestWorkflowHistory, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForAssignedDate = {};

        $scope.datePickerForAssignedDate.status = {
            opened: false
        };

        $scope.datePickerForAssignedDateOpen = function($event) {
            $scope.datePickerForAssignedDate.status.opened = true;
        };
        $scope.datePickerForActionedDate = {};

        $scope.datePickerForActionedDate.status = {
            opened: false
        };

        $scope.datePickerForActionedDateOpen = function($event) {
            $scope.datePickerForActionedDate.status.opened = true;
        };
}]);
