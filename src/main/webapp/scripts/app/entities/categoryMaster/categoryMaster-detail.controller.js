'use strict';

angular.module('watererpApp')
    .controller('CategoryMasterDetailController', function ($scope, $rootScope, $stateParams, entity, CategoryMaster) {
        $scope.categoryMaster = entity;
        $scope.load = function (id) {
            CategoryMaster.get({id: id}, function(result) {
                $scope.categoryMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:categoryMasterUpdate', function(event, result) {
            $scope.categoryMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
