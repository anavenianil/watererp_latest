'use strict';

angular.module('watererpApp')
    .controller('BillFullDetailsDetailController', function ($scope, $rootScope, $stateParams, entity, BillFullDetails) {
        $scope.billFullDetails = entity;
        $scope.load = function (id) {
            BillFullDetails.get({id: id}, function(result) {
                $scope.billFullDetails = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:billFullDetailsUpdate', function(event, result) {
            $scope.billFullDetails = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
