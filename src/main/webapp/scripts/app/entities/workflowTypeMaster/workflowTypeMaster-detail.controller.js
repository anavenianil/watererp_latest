'use strict';

angular.module('watererpApp')
    .controller('WorkflowTypeMasterDetailController', function ($scope, $rootScope, $stateParams, entity, WorkflowTypeMaster, StatusMaster) {
        $scope.workflowTypeMaster = entity;
        $scope.load = function (id) {
            WorkflowTypeMaster.get({id: id}, function(result) {
                $scope.workflowTypeMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:workflowTypeMasterUpdate', function(event, result) {
            $scope.workflowTypeMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
