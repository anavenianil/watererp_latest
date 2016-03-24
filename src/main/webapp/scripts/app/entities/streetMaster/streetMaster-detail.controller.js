'use strict';

angular.module('watererpApp')
    .controller('StreetMasterDetailController', function ($scope, $rootScope, $stateParams, entity, StreetMaster, ZoneMaster) {
        $scope.streetMaster = entity;
        $scope.load = function (id) {
            StreetMaster.get({id: id}, function(result) {
                $scope.streetMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:streetMasterUpdate', function(event, result) {
            $scope.streetMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
