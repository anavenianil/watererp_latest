'use strict';

angular.module('watererpApp')
    .controller('ReAllotmentDetailController', function ($scope, $rootScope, $stateParams, entity, ReAllotment, FileNumber, Customer, FeasibilityStatus) {
        $scope.reAllotment = entity;
        $scope.load = function (id) {
            ReAllotment.get({id: id}, function(result) {
                $scope.reAllotment = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:reAllotmentUpdate', function(event, result) {
            $scope.reAllotment = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
