'use strict';

angular.module('watererpApp')
    .controller('TerminalLogDetailController', function ($scope, $rootScope, $stateParams, entity, TerminalLog) {
        $scope.terminalLog = entity;
        $scope.load = function (id) {
            TerminalLog.get({id: id}, function(result) {
                $scope.terminalLog = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:terminalLogUpdate', function(event, result) {
            $scope.terminalLog = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
