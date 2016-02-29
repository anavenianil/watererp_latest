'use strict';

angular.module('watererpApp')
    .controller('WorkflowMasterDetailController', function ($scope, $rootScope, $stateParams, entity, WorkflowMaster, StatusMaster) {
        $scope.workflowMaster = entity;
        $scope.load = function (id) {
            WorkflowMaster.get({id: id}, function(result) {
                $scope.workflowMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:workflowMasterUpdate', function(event, result) {
            $scope.workflowMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
