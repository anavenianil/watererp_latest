'use strict';

angular.module('watererpApp')
    .controller('WorkflowStageMasterDetailController', function ($scope, $rootScope, $stateParams, entity, WorkflowStageMaster, StatusMaster) {
        $scope.workflowStageMaster = entity;
        $scope.load = function (id) {
            WorkflowStageMaster.get({id: id}, function(result) {
                $scope.workflowStageMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:workflowStageMasterUpdate', function(event, result) {
            $scope.workflowStageMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
