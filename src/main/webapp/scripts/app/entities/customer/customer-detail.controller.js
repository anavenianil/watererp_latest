'use strict';

angular.module('watererpApp')
    .controller('CustomerDetailController', function ($scope, $rootScope, $stateParams, entity, Customer, FileNumber) {
        $scope.customer = entity;
        $scope.load = function (id) {
            Customer.get({id: id}, function(result) {
                $scope.customer = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:customerUpdate', function(event, result) {
            $scope.customer = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
