'use strict';

angular.module('watererpApp')
    .controller('DivisionMasterDetailController', function ($scope, $rootScope, $stateParams, entity, DivisionMaster) {
        $scope.divisionMaster = entity;
        $scope.load = function (id) {
            DivisionMaster.get({id: id}, function(result) {
                $scope.divisionMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:divisionMasterUpdate', function(event, result) {
            $scope.divisionMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
