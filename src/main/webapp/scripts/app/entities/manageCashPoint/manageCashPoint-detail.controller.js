'use strict';

angular.module('watererpApp')
    .controller('ManageCashPointDetailController', function ($scope, $rootScope, $stateParams, entity, ManageCashPoint, TransactionTypeMaster, CashBookMaster, PaymentTypes, FileNumber, Customer) {
        $scope.manageCashPoint = entity;
        $scope.load = function (id) {
            ManageCashPoint.get({id: id}, function(result) {
                $scope.manageCashPoint = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:manageCashPointUpdate', function(event, result) {
            $scope.manageCashPoint = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
