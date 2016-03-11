'use strict';

angular.module('watererpApp')
    .controller('DocketCodeDetailController', function ($scope, $rootScope, $stateParams, entity, DocketCode) {
        $scope.docketCode = entity;
        $scope.load = function (id) {
            DocketCode.get({id: id}, function(result) {
                $scope.docketCode = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:docketCodeUpdate', function(event, result) {
            $scope.docketCode = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
