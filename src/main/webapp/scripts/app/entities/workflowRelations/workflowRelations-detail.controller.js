'use strict';

angular.module('watererpApp')
    .controller('WorkflowRelationsDetailController', function ($scope, $rootScope, $stateParams, entity, WorkflowRelations, StatusMaster) {
        $scope.workflowRelations = entity;
        $scope.load = function (id) {
            WorkflowRelations.get({id: id}, function(result) {
                $scope.workflowRelations = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:workflowRelationsUpdate', function(event, result) {
            $scope.workflowRelations = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
