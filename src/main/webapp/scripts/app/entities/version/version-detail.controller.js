'use strict';

angular.module('watererpApp')
    .controller('VersionDetailController', function ($scope, $rootScope, $stateParams, entity, Version) {
        $scope.version = entity;
        $scope.load = function (id) {
            Version.get({id: id}, function(result) {
                $scope.version = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:versionUpdate', function(event, result) {
            $scope.version = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
