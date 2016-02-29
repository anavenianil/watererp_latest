'use strict';

angular.module('watererpApp').controller('ReqDesigWorkflowMappingDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'ReqDesigWorkflowMapping', 'WorkflowMaster', 'RequestMaster', 'DesignationMaster', 'StatusMaster',
        function($scope, $stateParams, $uibModalInstance, entity, ReqDesigWorkflowMapping, WorkflowMaster, RequestMaster, DesignationMaster, StatusMaster) {

        $scope.reqDesigWorkflowMapping = entity;
        $scope.workflowmasters = WorkflowMaster.query();
        $scope.requestmasters = RequestMaster.query();
        $scope.designationmasters = DesignationMaster.query();
        $scope.statusmasters = StatusMaster.query();
        $scope.load = function(id) {
            ReqDesigWorkflowMapping.get({id : id}, function(result) {
                $scope.reqDesigWorkflowMapping = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:reqDesigWorkflowMappingUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.reqDesigWorkflowMapping.id != null) {
                ReqDesigWorkflowMapping.update($scope.reqDesigWorkflowMapping, onSaveSuccess, onSaveError);
            } else {
                ReqDesigWorkflowMapping.save($scope.reqDesigWorkflowMapping, onSaveSuccess, onSaveError);
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
