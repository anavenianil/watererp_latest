'use strict';

angular.module('watererpApp')
    .controller('MainWaterSizeDetailController', function ($scope, $rootScope, $stateParams, entity, MainWaterSize) {
        $scope.mainWaterSize = entity;
        $scope.load = function (id) {
            MainWaterSize.get({id: id}, function(result) {
                $scope.mainWaterSize = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:mainWaterSizeUpdate', function(event, result) {
            $scope.mainWaterSize = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
