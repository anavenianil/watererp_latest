'use strict';

angular.module('watererpApp')
    .controller('EmpMasterDetailController', function ($scope, $rootScope, $stateParams, entity, EmpMaster, User, OrgRoleInstance, DesignationMaster, StatusMaster) {
        $scope.empMaster = entity;
        $scope.load = function (id) {
            EmpMaster.get({id: id}, function(result) {
                $scope.empMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:empMasterUpdate', function(event, result) {
            $scope.empMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
