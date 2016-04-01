'use strict';

angular.module('watererpApp')
    .controller('TerminalDetailController', function ($scope, $rootScope, $stateParams, entity, Terminal) {
        $scope.terminal = entity;
        $scope.load = function (id) {
            Terminal.get({id: id}, function(result) {
                $scope.terminal = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:terminalUpdate', function(event, result) {
            $scope.terminal = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
