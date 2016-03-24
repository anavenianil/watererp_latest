'use strict';

angular.module('watererpApp')
    .controller('ZoneMasterDetailController', function ($scope, $rootScope, $stateParams, entity, ZoneMaster, DivisionMaster) {
        $scope.zoneMaster = entity;
        $scope.load = function (id) {
            ZoneMaster.get({id: id}, function(result) {
                $scope.zoneMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:zoneMasterUpdate', function(event, result) {
            $scope.zoneMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
