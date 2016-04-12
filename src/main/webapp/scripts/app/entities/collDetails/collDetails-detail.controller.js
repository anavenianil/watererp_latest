'use strict';

angular.module('watererpApp')
    .controller('CollDetailsDetailController', function ($scope, $rootScope, $stateParams, entity, CollDetails, PaymentTypes, InstrumentIssuerMaster) {
        $scope.collDetails = entity;
        $scope.load = function (id) {
            CollDetails.get({id: id}, function(result) {
                $scope.collDetails = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:collDetailsUpdate', function(event, result) {
            $scope.collDetails = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
