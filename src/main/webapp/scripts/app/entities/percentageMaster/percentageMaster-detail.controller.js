'use strict';

angular.module('watererpApp')
    .controller('PercentageMasterDetailController', function ($scope, $rootScope, $stateParams, entity, PercentageMaster) {
        $scope.percentageMaster = entity;
        $scope.load = function (id) {
            PercentageMaster.get({id: id}, function(result) {
                $scope.percentageMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:percentageMasterUpdate', function(event, result) {
            $scope.percentageMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
