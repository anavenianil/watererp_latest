'use strict';

angular.module('watererpApp')
    .controller('PipeSizeMasterDetailController', function ($scope, $rootScope, $stateParams, entity, PipeSizeMaster) {
        $scope.pipeSizeMaster = entity;
        $scope.load = function (id) {
            PipeSizeMaster.get({id: id}, function(result) {
                $scope.pipeSizeMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:pipeSizeMasterUpdate', function(event, result) {
            $scope.pipeSizeMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
