'use strict';

angular.module('watererpApp').controller('RequestWorkflowMappingDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'RequestWorkflowMapping', 'StatusMaster', 'WorkflowMaster', 'RequestMaster',
        function($scope, $stateParams, $uibModalInstance, entity, RequestWorkflowMapping, StatusMaster, WorkflowMaster, RequestMaster) {

        $scope.requestWorkflowMapping = entity;
        $scope.statusmasters = StatusMaster.query();
        $scope.workflowmasters = WorkflowMaster.query();
        $scope.requestmasters = RequestMaster.query();
        $scope.load = function(id) {
            RequestWorkflowMapping.get({id : id}, function(result) {
                $scope.requestWorkflowMapping = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:requestWorkflowMappingUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.requestWorkflowMapping.id != null) {
                RequestWorkflowMapping.update($scope.requestWorkflowMapping, onSaveSuccess, onSaveError);
            } else {
                RequestWorkflowMapping.save($scope.requestWorkflowMapping, onSaveSuccess, onSaveError);
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
