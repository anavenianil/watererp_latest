'use strict';

angular.module('watererpApp')
    .controller('RevenueTypeMasterDetailController', function ($scope, $rootScope, $stateParams, entity, RevenueTypeMaster) {
        $scope.revenueTypeMaster = entity;
        $scope.load = function (id) {
            RevenueTypeMaster.get({id: id}, function(result) {
                $scope.revenueTypeMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:revenueTypeMasterUpdate', function(event, result) {
            $scope.revenueTypeMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
