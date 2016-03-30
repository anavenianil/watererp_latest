'use strict';

angular.module('watererpApp')
    .controller('ItemSubCodeMasterDetailController', function ($scope, $rootScope, $stateParams, entity, ItemSubCodeMaster) {
        $scope.itemSubCodeMaster = entity;
        $scope.load = function (id) {
            ItemSubCodeMaster.get({id: id}, function(result) {
                $scope.itemSubCodeMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:itemSubCodeMasterUpdate', function(event, result) {
            $scope.itemSubCodeMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
