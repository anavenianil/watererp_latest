'use strict';

angular.module('watererpApp')
    .controller('UrlDetailController', function ($scope, $rootScope, $stateParams, entity, Url) {
        $scope.url = entity;
        $scope.load = function (id) {
            Url.get({id: id}, function(result) {
                $scope.url = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:urlUpdate', function(event, result) {
            $scope.url = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
