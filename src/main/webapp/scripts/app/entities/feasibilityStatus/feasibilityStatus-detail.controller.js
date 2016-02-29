'use strict';

angular.module('watererpApp')
    .controller('FeasibilityStatusDetailController', function ($scope, $rootScope, $stateParams, entity, FeasibilityStatus) {
        $scope.feasibilityStatus = entity;
        $scope.load = function (id) {
            FeasibilityStatus.get({id: id}, function(result) {
                $scope.feasibilityStatus = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:feasibilityStatusUpdate', function(event, result) {
            $scope.feasibilityStatus = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
