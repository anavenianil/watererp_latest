'use strict';

angular.module('watererpApp')
    .controller('MerchantMasterDetailController', function ($scope, $rootScope, $stateParams, entity, MerchantMaster) {
        $scope.merchantMaster = entity;
        $scope.load = function (id) {
            MerchantMaster.get({id: id}, function(result) {
                $scope.merchantMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:merchantMasterUpdate', function(event, result) {
            $scope.merchantMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
