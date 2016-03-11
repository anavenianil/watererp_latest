'use strict';

angular.module('watererpApp')
    .controller('SchemeMasterDetailController', function ($scope, $rootScope, $stateParams, entity, SchemeMaster) {
        $scope.schemeMaster = entity;
        $scope.load = function (id) {
            SchemeMaster.get({id: id}, function(result) {
                $scope.schemeMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:schemeMasterUpdate', function(event, result) {
            $scope.schemeMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
