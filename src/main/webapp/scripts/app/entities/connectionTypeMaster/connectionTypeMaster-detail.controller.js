'use strict';

angular.module('watererpApp')
    .controller('ConnectionTypeMasterDetailController', function ($scope, $rootScope, $stateParams, entity, ConnectionTypeMaster) {
        $scope.connectionTypeMaster = entity;
        $scope.load = function (id) {
            ConnectionTypeMaster.get({id: id}, function(result) {
                $scope.connectionTypeMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:connectionTypeMasterUpdate', function(event, result) {
            $scope.connectionTypeMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
