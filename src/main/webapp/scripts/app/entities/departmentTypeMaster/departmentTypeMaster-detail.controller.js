'use strict';

angular.module('watererpApp')
    .controller('DepartmentTypeMasterDetailController', function ($scope, $rootScope, $stateParams, entity, DepartmentTypeMaster, StatusMaster) {
        $scope.departmentTypeMaster = entity;
        $scope.load = function (id) {
            DepartmentTypeMaster.get({id: id}, function(result) {
                $scope.departmentTypeMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:departmentTypeMasterUpdate', function(event, result) {
            $scope.departmentTypeMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
