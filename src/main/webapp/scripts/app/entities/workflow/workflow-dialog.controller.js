'use strict';

angular.module('watererpApp').controller('WorkflowDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Workflow', 'WorkflowMaster', 'WorkflowRelations', 'OrgRoleInstance', 'WorkflowRelationships', 'WorkflowStageMaster',
        function($scope, $stateParams, $uibModalInstance, entity, Workflow, WorkflowMaster, WorkflowRelations, OrgRoleInstance, WorkflowRelationships, WorkflowStageMaster) {

        $scope.workflow = entity;
        $scope.workflowmasters = WorkflowMaster.query();
        $scope.workflowrelationss = WorkflowRelations.query();
        $scope.orgroleinstances = OrgRoleInstance.query();
        $scope.workflowrelationshipss = WorkflowRelationships.query();
        $scope.workflowstagemasters = WorkflowStageMaster.query();
        $scope.load = function(id) {
            Workflow.get({id : id}, function(result) {
                $scope.workflow = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:workflowUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.workflow.id != null) {
                Workflow.update($scope.workflow, onSaveSuccess, onSaveError);
            } else {
                Workflow.save($scope.workflow, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
