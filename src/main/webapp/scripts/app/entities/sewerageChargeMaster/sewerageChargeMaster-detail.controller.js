'use strict';

angular.module('watererpApp')
    .controller('SewerageChargeMasterDetailController', function ($scope, $rootScope, $stateParams, entity, SewerageChargeMaster, TariffCategoryMaster) {
        $scope.sewerageChargeMaster = entity;
        $scope.load = function (id) {
            SewerageChargeMaster.get({id: id}, function(result) {
                $scope.sewerageChargeMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:sewerageChargeMasterUpdate', function(event, result) {
            $scope.sewerageChargeMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
