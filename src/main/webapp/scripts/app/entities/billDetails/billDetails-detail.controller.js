'use strict';

angular.module('watererpApp')
    .controller('BillDetailsDetailController', function ($scope, $rootScope, $stateParams, entity, BillDetails, User) {
        $scope.billDetails = entity;
        $scope.load = function (id) {
            BillDetails.get({id: id}, function(result) {
                $scope.billDetails = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:billDetailsUpdate', function(event, result) {
            $scope.billDetails = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
