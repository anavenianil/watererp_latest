'use strict';

angular.module('watererpApp')
    .controller('ReversalDetailsDetailController', function ($scope, $rootScope, $stateParams, entity, ReversalDetails, CollDetails, User) {
        $scope.reversalDetails = entity;
        $scope.load = function (id) {
            ReversalDetails.get({id: id}, function(result) {
                $scope.reversalDetails = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:reversalDetailsUpdate', function(event, result) {
            $scope.reversalDetails = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
