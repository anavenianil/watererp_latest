'use strict';

angular.module('watererpApp')
    .controller('RoleWorkflowMappingDetailController', function ($scope, $rootScope, $stateParams, entity, RoleWorkflowMapping, StatusMaster, OrgRoleInstance, WorkflowMaster, RequestMaster) {
        $scope.roleWorkflowMapping = entity;
        $scope.load = function (id) {
            RoleWorkflowMapping.get({id: id}, function(result) {
                $scope.roleWorkflowMapping = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:roleWorkflowMappingUpdate', function(event, result) {
            $scope.roleWorkflowMapping = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
