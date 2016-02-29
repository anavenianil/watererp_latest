'use strict';

angular.module('watererpApp')
    .controller('FileNumberDetailController', function ($scope, $rootScope, $stateParams, entity, FileNumber) {
        $scope.fileNumber = entity;
        $scope.load = function (id) {
            FileNumber.get({id: id}, function(result) {
                $scope.fileNumber = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:fileNumberUpdate', function(event, result) {
            $scope.fileNumber = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
