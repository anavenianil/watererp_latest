'use strict';

angular.module('watererpApp')
    .controller('CurrentUsersDetailController', function ($scope, $rootScope, $stateParams, entity, CurrentUsers) {
        $scope.currentUsers = entity;
        $scope.load = function (id) {
            CurrentUsers.get({id: id}, function(result) {
                $scope.currentUsers = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:currentUsersUpdate', function(event, result) {
            $scope.currentUsers = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
