'use strict';

angular.module('watererpApp')
    .controller('TariffCategoryMasterDetailController', function ($scope, $rootScope, $stateParams, entity, TariffCategoryMaster) {
        $scope.tariffCategoryMaster = entity;
        $scope.load = function (id) {
            TariffCategoryMaster.get({id: id}, function(result) {
                $scope.tariffCategoryMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:tariffCategoryMasterUpdate', function(event, result) {
            $scope.tariffCategoryMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
