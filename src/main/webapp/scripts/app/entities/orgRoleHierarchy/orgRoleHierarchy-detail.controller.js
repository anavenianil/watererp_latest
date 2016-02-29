'use strict';

angular.module('watererpApp')
    .controller('OrgRoleHierarchyDetailController', function ($scope, $rootScope, $stateParams, entity, OrgRoleHierarchy, StatusMaster) {
        $scope.orgRoleHierarchy = entity;
        $scope.load = function (id) {
            OrgRoleHierarchy.get({id: id}, function(result) {
                $scope.orgRoleHierarchy = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:orgRoleHierarchyUpdate', function(event, result) {
            $scope.orgRoleHierarchy = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
