'use strict';

angular.module('watererpApp')
    .controller('BillRunMasterDetailController', function ($scope, $rootScope, $stateParams, entity, BillRunMaster) {
        $scope.billRunMaster = entity;
        $scope.load = function (id) {
            BillRunMaster.get({id: id}, function(result) {
                $scope.billRunMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:billRunMasterUpdate', function(event, result) {
            $scope.billRunMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
