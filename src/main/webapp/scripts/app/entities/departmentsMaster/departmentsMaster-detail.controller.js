'use strict';

angular.module('watererpApp')
    .controller('DepartmentsMasterDetailController', function ($scope, $rootScope, $stateParams, entity, DepartmentsMaster, DepartmentsHierarchy, DepartmentTypeMaster, StatusMaster) {
        $scope.departmentsMaster = entity;
        $scope.load = function (id) {
            DepartmentsMaster.get({id: id}, function(result) {
                $scope.departmentsMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:departmentsMasterUpdate', function(event, result) {
            $scope.departmentsMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
