'use strict';

angular.module('waterERPApp')
    .controller('TariffCategoryMasterDetailController', function ($scope, $rootScope, $stateParams, entity, TariffCategoryMaster) {
        $scope.tariffCategoryMaster = entity;
        $scope.load = function (id) {
            TariffCategoryMaster.get({id: id}, function(result) {
                $scope.tariffCategoryMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('waterERPApp:tariffCategoryMasterUpdate', function(event, result) {
            $scope.tariffCategoryMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
