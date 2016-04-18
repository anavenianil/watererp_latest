'use strict';

angular.module('watererpApp')
    .controller('CustMeterMappingDetailController', function ($scope, $rootScope, $stateParams, entity, CustMeterMapping, CustDetails, MeterDetails, User) {
        $scope.custMeterMapping = entity;
        $scope.load = function (id) {
            CustMeterMapping.get({id: id}, function(result) {
                $scope.custMeterMapping = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:custMeterMappingUpdate', function(event, result) {
            $scope.custMeterMapping = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
