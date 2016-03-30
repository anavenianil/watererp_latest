'use strict';

angular.module('waterERPApp')
    .controller('TariffTypeMasterDetailController', function ($scope, $rootScope, $stateParams, entity, TariffTypeMaster) {
        $scope.tariffTypeMaster = entity;
        $scope.load = function (id) {
            TariffTypeMaster.get({id: id}, function(result) {
                $scope.tariffTypeMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('waterERPApp:tariffTypeMasterUpdate', function(event, result) {
            $scope.tariffTypeMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
