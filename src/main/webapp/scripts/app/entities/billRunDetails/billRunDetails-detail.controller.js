'use strict';

angular.module('watererpApp')
    .controller('BillRunDetailsDetailController', function ($scope, $rootScope, $stateParams, entity, BillRunDetails, BillFullDetails) {
        $scope.billRunDetails = entity;
        $scope.load = function (id) {
            BillRunDetails.get({id: id}, function(result) {
                $scope.billRunDetails = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:billRunDetailsUpdate', function(event, result) {
            $scope.billRunDetails = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
