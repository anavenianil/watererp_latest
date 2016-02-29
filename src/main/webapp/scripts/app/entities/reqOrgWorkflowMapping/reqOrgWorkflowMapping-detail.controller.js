'use strict';

angular.module('watererpApp')
    .controller('ReqOrgWorkflowMappingDetailController', function ($scope, $rootScope, $stateParams, entity, ReqOrgWorkflowMapping, WorkflowMaster, RequestMaster, OrgRoleInstance, StatusMaster) {
        $scope.reqOrgWorkflowMapping = entity;
        $scope.load = function (id) {
            ReqOrgWorkflowMapping.get({id: id}, function(result) {
                $scope.reqOrgWorkflowMapping = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:reqOrgWorkflowMappingUpdate', function(event, result) {
            $scope.reqOrgWorkflowMapping = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
