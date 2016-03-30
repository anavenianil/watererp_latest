'use strict';

angular.module('watererpApp')
    .controller('ItemSubCategoryMasterDetailController', function ($scope, $rootScope, $stateParams, entity, ItemSubCategoryMaster, ItemCategoryMaster) {
        $scope.itemSubCategoryMaster = entity;
        $scope.load = function (id) {
            ItemSubCategoryMaster.get({id: id}, function(result) {
                $scope.itemSubCategoryMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:itemSubCategoryMasterUpdate', function(event, result) {
            $scope.itemSubCategoryMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
