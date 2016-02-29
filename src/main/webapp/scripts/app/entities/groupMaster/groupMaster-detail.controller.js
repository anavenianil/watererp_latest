'use strict';

angular.module('watererpApp')
    .controller('GroupMasterDetailController', function ($scope, $rootScope, $stateParams, entity, GroupMaster, StatusMaster) {
        $scope.groupMaster = entity;
        $scope.load = function (id) {
            GroupMaster.get({id: id}, function(result) {
                $scope.groupMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:groupMasterUpdate', function(event, result) {
            $scope.groupMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
