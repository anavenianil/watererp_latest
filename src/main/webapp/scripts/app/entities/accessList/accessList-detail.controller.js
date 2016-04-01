'use strict';

angular.module('watererpApp')
    .controller('AccessListDetailController', function ($scope, $rootScope, $stateParams, entity, AccessList) {
        $scope.accessList = entity;
        $scope.load = function (id) {
            AccessList.get({id: id}, function(result) {
                $scope.accessList = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:accessListUpdate', function(event, result) {
            $scope.accessList = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
