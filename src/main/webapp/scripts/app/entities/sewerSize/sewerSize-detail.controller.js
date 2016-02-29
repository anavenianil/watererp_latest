'use strict';

angular.module('watererpApp')
    .controller('SewerSizeDetailController', function ($scope, $rootScope, $stateParams, entity, SewerSize) {
        $scope.sewerSize = entity;
        $scope.load = function (id) {
            SewerSize.get({id: id}, function(result) {
                $scope.sewerSize = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:sewerSizeUpdate', function(event, result) {
            $scope.sewerSize = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
