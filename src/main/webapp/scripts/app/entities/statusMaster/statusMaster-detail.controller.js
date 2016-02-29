'use strict';

angular.module('watererpApp')
    .controller('StatusMasterDetailController', function ($scope, $rootScope, $stateParams, entity, StatusMaster) {
        $scope.statusMaster = entity;
        $scope.load = function (id) {
            StatusMaster.get({id: id}, function(result) {
                $scope.statusMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:statusMasterUpdate', function(event, result) {
            $scope.statusMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
