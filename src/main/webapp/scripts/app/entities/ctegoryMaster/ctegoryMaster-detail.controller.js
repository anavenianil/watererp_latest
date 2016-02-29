'use strict';

angular.module('watererpApp')
    .controller('CtegoryMasterDetailController', function ($scope, $rootScope, $stateParams, entity, CtegoryMaster) {
        $scope.ctegoryMaster = entity;
        $scope.load = function (id) {
            CtegoryMaster.get({id: id}, function(result) {
                $scope.ctegoryMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:ctegoryMasterUpdate', function(event, result) {
            $scope.ctegoryMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
