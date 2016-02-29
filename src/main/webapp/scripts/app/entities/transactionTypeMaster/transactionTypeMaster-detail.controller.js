'use strict';

angular.module('watererpApp')
    .controller('TransactionTypeMasterDetailController', function ($scope, $rootScope, $stateParams, entity, TransactionTypeMaster) {
        $scope.transactionTypeMaster = entity;
        $scope.load = function (id) {
            TransactionTypeMaster.get({id: id}, function(result) {
                $scope.transactionTypeMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:transactionTypeMasterUpdate', function(event, result) {
            $scope.transactionTypeMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
