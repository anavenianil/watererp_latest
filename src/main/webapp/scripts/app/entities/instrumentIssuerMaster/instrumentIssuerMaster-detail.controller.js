'use strict';

angular.module('watererpApp')
    .controller('BankMasterDetailController', function ($scope, $rootScope, $stateParams, entity, BankMaster) {
        $scope.instrumentIssuerMaster = entity;
        $scope.load = function (id) {
            BankMaster.get({id: id}, function(result) {
                $scope.instrumentIssuerMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:instrumentIssuerMasterUpdate', function(event, result) {
            $scope.instrumentIssuerMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
