'use strict';

angular.module('watererpApp')
    .controller('WorkflowDetailController', function ($scope, $rootScope, $stateParams, entity, Workflow, WorkflowMaster, WorkflowRelations, OrgRoleInstance, WorkflowRelationships, WorkflowStageMaster) {
        $scope.workflow = entity;
        $scope.load = function (id) {
            Workflow.get({id: id}, function(result) {
                $scope.workflow = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:workflowUpdate', function(event, result) {
            $scope.workflow = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
