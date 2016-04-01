'use strict';

angular.module('watererpApp')
    .controller('BillOfMaterialDetailController', function ($scope, $rootScope, $stateParams, entity, BillOfMaterial, ApplicationTxn, PaymentTypes) {
        $scope.billOfMaterial = entity;
        $scope.load = function (id) {
            BillOfMaterial.get({id: id}, function(result) {
                $scope.billOfMaterial = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:billOfMaterialUpdate', function(event, result) {
            $scope.billOfMaterial = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
