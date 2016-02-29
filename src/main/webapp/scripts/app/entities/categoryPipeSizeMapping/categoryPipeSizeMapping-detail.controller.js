'use strict';

angular.module('watererpApp')
    .controller('CategoryPipeSizeMappingDetailController', function ($scope, $rootScope, $stateParams, entity, CategoryPipeSizeMapping, CtegoryMaster, PipeSizeMaster) {
        $scope.categoryPipeSizeMapping = entity;
        $scope.load = function (id) {
            CategoryPipeSizeMapping.get({id: id}, function(result) {
                $scope.categoryPipeSizeMapping = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:categoryPipeSizeMappingUpdate', function(event, result) {
            $scope.categoryPipeSizeMapping = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
