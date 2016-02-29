'use strict';

angular.module('watererpApp')
    .controller('DepartmentsHierarchyDetailController', function ($scope, $rootScope, $stateParams, entity, DepartmentsHierarchy, StatusMaster) {
        $scope.departmentsHierarchy = entity;
        $scope.load = function (id) {
            DepartmentsHierarchy.get({id: id}, function(result) {
                $scope.departmentsHierarchy = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:departmentsHierarchyUpdate', function(event, result) {
            $scope.departmentsHierarchy = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
