'use strict';

angular.module('watererpApp')
    .controller('UomDetailController', function ($scope, $rootScope, $stateParams, entity, Uom) {
        $scope.uom = entity;
        $scope.load = function (id) {
            Uom.get({id: id}, function(result) {
                $scope.uom = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:uomUpdate', function(event, result) {
            $scope.uom = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
