'use strict';

angular.module('watererpApp')
    .controller('MaterialMasterDetailController', function ($scope, $rootScope, $stateParams, entity, MaterialMaster) {
        $scope.materialMaster = entity;
        $scope.load = function (id) {
            MaterialMaster.get({id: id}, function(result) {
                $scope.materialMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:materialMasterUpdate', function(event, result) {
            $scope.materialMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
