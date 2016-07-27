'use strict';

angular.module('watererpApp')
    .controller('BankMasterDetailController', function ($scope, $rootScope, $stateParams, entity, BankMaster) {
        $scope.bankMaster = entity;
        $scope.load = function (id) {
            BankMaster.get({id: id}, function(result) {
                $scope.bankMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:bankMasterUpdate', function(event, result) {
            $scope.bankMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
