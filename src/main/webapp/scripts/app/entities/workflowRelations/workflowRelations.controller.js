'use strict';

angular.module('watererpApp')
    .controller('WorkflowRelationsController', function ($scope, $state, WorkflowRelations) {

        $scope.workflowRelationss = [];
        $scope.loadAll = function() {
            WorkflowRelations.query(function(result) {
               $scope.workflowRelationss = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.workflowRelations = {
                name: null,
                id: null
            };
        };
    });
