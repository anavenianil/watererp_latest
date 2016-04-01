'use strict';

angular.module('watererpApp')
    .controller('TariffChargesDetailController', function ($scope, $rootScope, $stateParams, entity, TariffCharges, TariffMaster, TariffTypeMaster) {
        $scope.tariffCharges = entity;
        $scope.load = function (id) {
            TariffCharges.get({id: id}, function(result) {
                $scope.tariffCharges = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:tariffChargesUpdate', function(event, result) {
            $scope.tariffCharges = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
