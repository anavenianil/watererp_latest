'use strict';

angular.module('watererpApp')
    .controller('MakeOfPipeDetailController', function ($scope, $rootScope, $stateParams, entity, MakeOfPipe) {
        $scope.makeOfPipe = entity;
        $scope.load = function (id) {
            MakeOfPipe.get({id: id}, function(result) {
                $scope.makeOfPipe = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:makeOfPipeUpdate', function(event, result) {
            $scope.makeOfPipe = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
