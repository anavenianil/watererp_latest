'use strict';

angular.module('watererpApp')
    .controller('OrgRolesMasterDetailController', function ($scope, $rootScope, $stateParams, entity, OrgRolesMaster, StatusMaster) {
        $scope.orgRolesMaster = entity;
        $scope.load = function (id) {
            OrgRolesMaster.get({id: id}, function(result) {
                $scope.orgRolesMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:orgRolesMasterUpdate', function(event, result) {
            $scope.orgRolesMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
