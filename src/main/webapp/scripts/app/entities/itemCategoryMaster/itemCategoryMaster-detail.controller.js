'use strict';

angular.module('watererpApp')
    .controller('ItemCategoryMasterDetailController', function ($scope, $rootScope, $stateParams, entity, ItemCategoryMaster) {
        $scope.itemCategoryMaster = entity;
        $scope.load = function (id) {
            ItemCategoryMaster.get({id: id}, function(result) {
                $scope.itemCategoryMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:itemCategoryMasterUpdate', function(event, result) {
            $scope.itemCategoryMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
