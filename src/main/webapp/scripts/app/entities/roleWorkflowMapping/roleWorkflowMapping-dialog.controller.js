'use strict';

angular.module('watererpApp').controller('RoleWorkflowMappingDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'RoleWorkflowMapping', 'StatusMaster', 'OrgRoleInstance', 'WorkflowMaster', 'RequestMaster',
        function($scope, $stateParams, $uibModalInstance, entity, RoleWorkflowMapping, StatusMaster, OrgRoleInstance, WorkflowMaster, RequestMaster) {

        $scope.roleWorkflowMapping = entity;
        $scope.statusmasters = StatusMaster.query();
        $scope.orgroleinstances = OrgRoleInstance.query();
        $scope.workflowmasters = WorkflowMaster.query();
        $scope.requestmasters = RequestMaster.query();
        $scope.load = function(id) {
            RoleWorkflowMapping.get({id : id}, function(result) {
                $scope.roleWorkflowMapping = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:roleWorkflowMappingUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.roleWorkflowMapping.id != null) {
                RoleWorkflowMapping.update($scope.roleWorkflowMapping, onSaveSuccess, onSaveError);
            } else {
                RoleWorkflowMapping.save($scope.roleWorkflowMapping, onSaveSuccess, onSaveError);
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
