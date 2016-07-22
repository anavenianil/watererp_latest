'use strict';

angular.module('watererpApp')
    .controller('ChargeBaseDetailController', function ($scope, $rootScope, $stateParams, entity, ChargeBase) {
        $scope.chargeBase = entity;
        $scope.load = function (id) {
            ChargeBase.get({id: id}, function(result) {
                $scope.chargeBase = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:chargeBaseUpdate', function(event, result) {
            $scope.chargeBase = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
