'use strict';

angular.module('watererpApp')
    .controller('ItemCompanyMasterDetailController', function ($scope, $rootScope, $stateParams, entity, ItemCompanyMaster) {
        $scope.itemCompanyMaster = entity;
        $scope.load = function (id) {
            ItemCompanyMaster.get({id: id}, function(result) {
                $scope.itemCompanyMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:itemCompanyMasterUpdate', function(event, result) {
            $scope.itemCompanyMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
