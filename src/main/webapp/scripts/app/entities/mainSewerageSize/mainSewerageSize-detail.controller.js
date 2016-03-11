'use strict';

angular.module('watererpApp')
    .controller('MainSewerageSizeDetailController', function ($scope, $rootScope, $stateParams, entity, MainSewerageSize) {
        $scope.mainSewerageSize = entity;
        $scope.load = function (id) {
            MainSewerageSize.get({id: id}, function(result) {
                $scope.mainSewerageSize = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:mainSewerageSizeUpdate', function(event, result) {
            $scope.mainSewerageSize = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
