'use strict';

angular.module('watererpApp')
    .controller('RequestMasterDetailController', function ($scope, $rootScope, $stateParams, entity, RequestMaster, StatusMaster, Module) {
        $scope.requestMaster = entity;
        $scope.load = function (id) {
            RequestMaster.get({id: id}, function(result) {
                $scope.requestMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:requestMasterUpdate', function(event, result) {
            $scope.requestMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
