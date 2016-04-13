'use strict';

angular.module('watererpApp')
    .controller('CollectionTypeMasterDetailController', function ($scope, $rootScope, $stateParams, entity, CollectionTypeMaster) {
        $scope.collectionTypeMaster = entity;
        $scope.load = function (id) {
            CollectionTypeMaster.get({id: id}, function(result) {
                $scope.collectionTypeMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:collectionTypeMasterUpdate', function(event, result) {
            $scope.collectionTypeMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
