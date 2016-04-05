'use strict';

angular.module('waterERPApp')
    .controller('CustMeterMappingDetailController', function ($scope, $rootScope, $stateParams, entity, CustMeterMapping, CustDetails, MeterDetails) {
        $scope.custMeterMapping = entity;
        $scope.load = function (id) {
            CustMeterMapping.get({id: id}, function(result) {
                $scope.custMeterMapping = result;
            });
        };
        var unsubscribe = $rootScope.$on('waterERPApp:custMeterMappingUpdate', function(event, result) {
            $scope.custMeterMapping = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
