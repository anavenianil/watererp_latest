'use strict';

angular.module('watererpApp')
    .controller('Module2MenuItemDetailController', function ($scope, $rootScope, $stateParams, entity, Module2MenuItem, Module, MenuItem) {
        $scope.module2MenuItem = entity;
        $scope.load = function (id) {
            Module2MenuItem.get({id: id}, function(result) {
                $scope.module2MenuItem = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:module2MenuItemUpdate', function(event, result) {
            $scope.module2MenuItem = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
