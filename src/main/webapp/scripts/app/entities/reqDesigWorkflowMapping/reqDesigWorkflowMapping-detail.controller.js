'use strict';

angular.module('watererpApp')
    .controller('ReqDesigWorkflowMappingDetailController', function ($scope, $rootScope, $stateParams, entity, ReqDesigWorkflowMapping, WorkflowMaster, RequestMaster, DesignationMaster, StatusMaster) {
        $scope.reqDesigWorkflowMapping = entity;
        $scope.load = function (id) {
            ReqDesigWorkflowMapping.get({id: id}, function(result) {
                $scope.reqDesigWorkflowMapping = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:reqDesigWorkflowMappingUpdate', function(event, result) {
            $scope.reqDesigWorkflowMapping = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
