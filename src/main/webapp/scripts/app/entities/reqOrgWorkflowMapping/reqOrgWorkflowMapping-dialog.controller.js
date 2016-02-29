'use strict';

angular.module('watererpApp').controller('ReqOrgWorkflowMappingDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'ReqOrgWorkflowMapping', 'WorkflowMaster', 'RequestMaster', 'OrgRoleInstance', 'StatusMaster',
        function($scope, $stateParams, $uibModalInstance, entity, ReqOrgWorkflowMapping, WorkflowMaster, RequestMaster, OrgRoleInstance, StatusMaster) {

        $scope.reqOrgWorkflowMapping = entity;
        $scope.workflowmasters = WorkflowMaster.query();
        $scope.requestmasters = RequestMaster.query();
        $scope.orgroleinstances = OrgRoleInstance.query();
        $scope.statusmasters = StatusMaster.query();
        $scope.load = function(id) {
            ReqOrgWorkflowMapping.get({id : id}, function(result) {
                $scope.reqOrgWorkflowMapping = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:reqOrgWorkflowMappingUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.reqOrgWorkflowMapping.id != null) {
                ReqOrgWorkflowMapping.update($scope.reqOrgWorkflowMapping, onSaveSuccess, onSaveError);
            } else {
                ReqOrgWorkflowMapping.save($scope.reqOrgWorkflowMapping, onSaveSuccess, onSaveError);
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
