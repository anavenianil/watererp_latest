'use strict';

angular.module('watererpApp')
    .controller('ItemDetailsDetailController', function ($scope, $rootScope, $stateParams, entity, ItemDetails) {
        $scope.itemDetails = entity;
        $scope.load = function (id) {
            ItemDetails.get({id: id}, function(result) {
                $scope.itemDetails = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:itemDetailsUpdate', function(event, result) {
            $scope.itemDetails = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
