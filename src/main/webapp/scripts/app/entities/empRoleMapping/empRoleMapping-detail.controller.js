'use strict';

angular.module('watererpApp')
    .controller('EmpRoleMappingDetailController', function ($scope, $rootScope, $stateParams, entity, EmpRoleMapping, User, OrgRoleInstance, StatusMaster) {
        $scope.empRoleMapping = entity;
        $scope.load = function (id) {
            EmpRoleMapping.get({id: id}, function(result) {
                $scope.empRoleMapping = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:empRoleMappingUpdate', function(event, result) {
            $scope.empRoleMapping = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
