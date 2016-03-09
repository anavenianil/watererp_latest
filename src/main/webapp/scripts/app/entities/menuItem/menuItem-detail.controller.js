'use strict';

angular.module('watererpApp')
    .controller('MenuItemDetailController', function ($scope, $rootScope, $stateParams, entity, MenuItem) {
        $scope.menuItem = entity;
        $scope.load = function (id) {
            MenuItem.get({id: id}, function(result) {
                $scope.menuItem = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:menuItemUpdate', function(event, result) {
            $scope.menuItem = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
