'use strict';

angular.module('watererpApp')
    .controller('MeterStatusDetailController', function ($scope, $rootScope, $stateParams, entity, MeterStatus) {
        $scope.meterStatus = entity;
        $scope.load = function (id) {
            MeterStatus.get({id: id}, function(result) {
                $scope.meterStatus = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:meterStatusUpdate', function(event, result) {
            $scope.meterStatus = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
