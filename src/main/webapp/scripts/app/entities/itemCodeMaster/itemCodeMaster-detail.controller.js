'use strict';

angular.module('watererpApp')
    .controller('ItemCodeMasterDetailController', function ($scope, $rootScope, $stateParams, entity, ItemCodeMaster, ItemCategoryMaster, ItemSubCategoryMaster) {
        $scope.itemCodeMaster = entity;
        $scope.load = function (id) {
            ItemCodeMaster.get({id: id}, function(result) {
                $scope.itemCodeMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:itemCodeMasterUpdate', function(event, result) {
            $scope.itemCodeMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
