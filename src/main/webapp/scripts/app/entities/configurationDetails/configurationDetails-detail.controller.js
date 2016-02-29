'use strict';

angular.module('watererpApp')
    .controller('ConfigurationDetailsDetailController', function ($scope, $rootScope, $stateParams, entity, ConfigurationDetails) {
        $scope.configurationDetails = entity;
        $scope.load = function (id) {
            ConfigurationDetails.get({id: id}, function(result) {
                $scope.configurationDetails = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:configurationDetailsUpdate', function(event, result) {
            $scope.configurationDetails = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
