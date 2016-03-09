'use strict';

angular.module('watererpApp')
    .controller('Url2RoleDetailController', function ($scope, $rootScope, $stateParams, entity, Url2Role, Url, Authority) {
        $scope.url2Role = entity;
        $scope.load = function (id) {
            Url2Role.get({id: id}, function(result) {
                $scope.url2Role = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:url2RoleUpdate', function(event, result) {
            $scope.url2Role = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
