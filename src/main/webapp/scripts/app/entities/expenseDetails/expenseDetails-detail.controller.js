'use strict';

angular.module('watererpApp')
    .controller('ExpenseDetailsDetailController', function ($scope, $rootScope, $stateParams, entity, ExpenseDetails, PaymentTypes, BankMaster, CollectionTypeMaster) {
        $scope.expenseDetails = entity;
        $scope.load = function (id) {
            ExpenseDetails.get({id: id}, function(result) {
                $scope.expenseDetails = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:expenseDetailsUpdate', function(event, result) {
            $scope.expenseDetails = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
