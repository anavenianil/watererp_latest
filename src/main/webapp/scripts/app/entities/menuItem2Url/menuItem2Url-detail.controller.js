'use strict';

angular.module('watererpApp')
    .controller('MenuItem2UrlDetailController', function ($scope, $rootScope, $stateParams, entity, MenuItem2Url, MenuItem, Url) {
        $scope.menuItem2Url = entity;
        $scope.load = function (id) {
            MenuItem2Url.get({id: id}, function(result) {
                $scope.menuItem2Url = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:menuItem2UrlUpdate', function(event, result) {
            $scope.menuItem2Url = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
