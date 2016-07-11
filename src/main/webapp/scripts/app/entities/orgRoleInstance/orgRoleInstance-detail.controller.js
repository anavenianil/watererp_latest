'use strict';

angular.module('watererpApp')
    .controller('OrgRoleInstanceDetailController', function ($scope, $rootScope, $stateParams, entity, OrgRoleInstance, StatusMaster, OrgRoleHierarchy/*, DepartmentsMaster*/) {
        $scope.orgRoleInstance = entity;
        $scope.load = function (id) {
            OrgRoleInstance.get({id: id}, function(result) {
                $scope.orgRoleInstance = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:orgRoleInstanceUpdate', function(event, result) {
            $scope.orgRoleInstance = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
