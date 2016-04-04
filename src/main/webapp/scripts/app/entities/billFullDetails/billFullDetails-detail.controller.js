'use strict';

angular.module('waterERPApp')
    .controller('BillFullDetailsDetailController', function ($scope, $rootScope, $stateParams, entity, BillFullDetails) {
        $scope.billFullDetails = entity;
        $scope.load = function (id) {
            BillFullDetails.get({id: id}, function(result) {
                $scope.billFullDetails = result;
            });
        };
        var unsubscribe = $rootScope.$on('waterERPApp:billFullDetailsUpdate', function(event, result) {
            $scope.billFullDetails = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
