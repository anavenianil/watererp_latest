'use strict';

angular.module('watererpApp')
    .controller('InstrumentIssuerMasterDetailController', function ($scope, $rootScope, $stateParams, entity, InstrumentIssuerMaster) {
        $scope.instrumentIssuerMaster = entity;
        $scope.load = function (id) {
            InstrumentIssuerMaster.get({id: id}, function(result) {
                $scope.instrumentIssuerMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:instrumentIssuerMasterUpdate', function(event, result) {
            $scope.instrumentIssuerMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
