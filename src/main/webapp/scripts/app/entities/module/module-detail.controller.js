'use strict';

angular.module('watererpApp')
    .controller('ModuleDetailController', function ($scope, $rootScope, $stateParams, entity, Module) {
        $scope.module = entity;
        $scope.load = function (id) {
            Module.get({id: id}, function(result) {
                $scope.module = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:moduleUpdate', function(event, result) {
            $scope.module = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
