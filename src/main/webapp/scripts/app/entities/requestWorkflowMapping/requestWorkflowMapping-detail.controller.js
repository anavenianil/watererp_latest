'use strict';

angular.module('watererpApp')
    .controller('RequestWorkflowMappingDetailController', function ($scope, $rootScope, $stateParams, entity, RequestWorkflowMapping, StatusMaster, WorkflowMaster, RequestMaster) {
        $scope.requestWorkflowMapping = entity;
        $scope.load = function (id) {
            RequestWorkflowMapping.get({id: id}, function(result) {
                $scope.requestWorkflowMapping = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:requestWorkflowMappingUpdate', function(event, result) {
            $scope.requestWorkflowMapping = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
