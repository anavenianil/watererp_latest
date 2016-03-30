'use strict';

angular.module('waterERPApp')
    .controller('TariffMasterDetailController', function ($scope, $rootScope, $stateParams, entity, TariffMaster, TariffCategoryMaster) {
        $scope.tariffMaster = entity;
        $scope.load = function (id) {
            TariffMaster.get({id: id}, function(result) {
                $scope.tariffMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('waterERPApp:tariffMasterUpdate', function(event, result) {
            $scope.tariffMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
