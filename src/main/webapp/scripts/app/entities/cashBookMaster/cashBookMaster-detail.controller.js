'use strict';

angular.module('watererpApp')
    .controller('CashBookMasterDetailController', function ($scope, $rootScope, $stateParams, entity, CashBookMaster) {
        $scope.cashBookMaster = entity;
        $scope.load = function (id) {
            CashBookMaster.get({id: id}, function(result) {
                $scope.cashBookMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:cashBookMasterUpdate', function(event, result) {
            $scope.cashBookMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
