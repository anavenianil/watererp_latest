'use strict';

angular.module('watererpApp')
    .controller('AdjustmentsDetailController', function ($scope, $rootScope, $stateParams, entity, Adjustments, CustDetails, BillFullDetails, TransactionTypeMaster, User, ComplaintTypeMaster) {
        $scope.adjustments = entity;
        $scope.load = function (id) {
            Adjustments.get({id: id}, function(result) {
                $scope.adjustments = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:adjustmentsUpdate', function(event, result) {
            $scope.adjustments = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
