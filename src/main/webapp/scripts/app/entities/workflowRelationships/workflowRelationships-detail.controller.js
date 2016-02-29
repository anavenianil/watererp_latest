'use strict';

angular.module('watererpApp')
    .controller('WorkflowRelationshipsDetailController', function ($scope, $rootScope, $stateParams, entity, WorkflowRelationships, StatusMaster) {
        $scope.workflowRelationships = entity;
        $scope.load = function (id) {
            WorkflowRelationships.get({id: id}, function(result) {
                $scope.workflowRelationships = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:workflowRelationshipsUpdate', function(event, result) {
            $scope.workflowRelationships = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
