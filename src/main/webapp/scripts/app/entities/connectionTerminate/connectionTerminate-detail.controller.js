'use strict';

angular.module('watererpApp')
    .controller('ConnectionTerminateDetailController', function ($scope, $rootScope, $stateParams, entity, ConnectionTerminate, MeterDetails) {
        $scope.connectionTerminate = entity;
        $scope.load = function (id) {
            ConnectionTerminate.get({id: id}, function(result) {
                $scope.connectionTerminate = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:connectionTerminateUpdate', function(event, result) {
            $scope.connectionTerminate = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
