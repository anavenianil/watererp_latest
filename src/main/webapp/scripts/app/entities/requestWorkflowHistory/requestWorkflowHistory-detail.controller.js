'use strict';

angular.module('watererpApp')
    .controller('RequestWorkflowHistoryDetailController', function ($scope, $rootScope, $stateParams, entity, RequestWorkflowHistory, User, StatusMaster, RequestMaster, WorkflowMaster, WorkflowStageMaster) {
        $scope.requestWorkflowHistory = entity;
        $scope.load = function (id) {
            RequestWorkflowHistory.get({id: id}, function(result) {
                $scope.requestWorkflowHistory = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:requestWorkflowHistoryUpdate', function(event, result) {
            $scope.requestWorkflowHistory = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
