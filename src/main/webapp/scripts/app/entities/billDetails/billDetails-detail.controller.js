'use strict';

angular.module('waterERPApp')
    .controller('BillDetailsDetailController', function ($scope, $rootScope, $stateParams, entity, BillDetails) {
        $scope.billDetails = entity;
        $scope.load = function (id) {
            BillDetails.get({id: id}, function(result) {
                $scope.billDetails = result;
            });
        };
        var unsubscribe = $rootScope.$on('waterERPApp:billDetailsUpdate', function(event, result) {
            $scope.billDetails = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
