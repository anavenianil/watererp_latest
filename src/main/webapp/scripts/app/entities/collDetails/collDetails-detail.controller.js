'use strict';

angular.module('waterERPApp')
    .controller('CollDetailsDetailController', function ($scope, $rootScope, $stateParams, entity, CollDetails) {
        $scope.collDetails = entity;
        $scope.load = function (id) {
            CollDetails.get({id: id}, function(result) {
                $scope.collDetails = result;
            });
        };
        var unsubscribe = $rootScope.$on('waterERPApp:collDetailsUpdate', function(event, result) {
            $scope.collDetails = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
