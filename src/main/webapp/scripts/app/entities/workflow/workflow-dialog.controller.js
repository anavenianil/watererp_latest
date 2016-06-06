'use strict';

angular.module('watererpApp').controller('WorkflowDialogController',
    /*['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Workflow', 'WorkflowMaster', 'WorkflowRelations', 'OrgRoleInstance', 'WorkflowRelationships', 'WorkflowStageMaster',*/
        function($scope, $state, $stateParams, /*$uibModalInstance, entity,*/ Workflow, WorkflowMaster, WorkflowRelations, OrgRoleInstance, WorkflowRelationships, WorkflowStageMaster, ParseLinks) {

        $scope.workflow = {};
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
        
        if($stateParams.id != null){
        	$scope.load($stateParams.id);
        }

        $scope.getWorkflow = function(workflowMasterId) {
            Workflow.query({page: $scope.page, size: 20, workflowMasterId:workflowMasterId, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.workflows.push(result[i]);
                }
            });
        };
        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:workflowUpdate', result);
            //$uibModalInstance.close(result);
            $scope.isSaving = false;
            $state.go('workflow');
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
}/*]*/);
