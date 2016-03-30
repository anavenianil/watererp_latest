'use strict';

angular.module('watererpApp')
    .controller('SibEntryDetailController', function ($scope, $rootScope, $stateParams, entity, SibEntry) {
        $scope.sibEntry = entity;
        $scope.load = function (id) {
            SibEntry.get({id: id}, function(result) {
                $scope.sibEntry = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:sibEntryUpdate', function(event, result) {
            $scope.sibEntry = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
