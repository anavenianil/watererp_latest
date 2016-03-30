'use strict';

angular.module('watererpApp')
    .controller('ItemRequiredDetailController', function ($scope, $rootScope, $stateParams, entity, ItemRequired, MaterialMaster, ApplicationTxn, FeasibilityStudy, Proceedings) {
        $scope.itemRequired = entity;
        $scope.load = function (id) {
            ItemRequired.get({id: id}, function(result) {
                $scope.itemRequired = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:itemRequiredUpdate', function(event, result) {
            $scope.itemRequired = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
