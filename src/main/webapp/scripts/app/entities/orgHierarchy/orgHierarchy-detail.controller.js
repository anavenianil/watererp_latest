'use strict';

angular.module('watererpApp')
    .controller('OrgHierarchyDetailController', function ($scope, $rootScope, $stateParams, entity, OrgHierarchy, StatusMaster) {
        $scope.orgHierarchy = entity;
        $scope.load = function (id) {
            OrgHierarchy.get({id: id}, function(result) {
                $scope.orgHierarchy = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:orgHierarchyUpdate', function(event, result) {
            $scope.orgHierarchy = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
