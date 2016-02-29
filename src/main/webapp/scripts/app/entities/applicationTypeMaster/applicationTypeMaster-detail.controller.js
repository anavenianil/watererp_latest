'use strict';

angular.module('watererpApp')
    .controller('ApplicationTypeMasterDetailController', function ($scope, $rootScope, $stateParams, entity, ApplicationTypeMaster) {
        $scope.applicationTypeMaster = entity;
        $scope.load = function (id) {
            ApplicationTypeMaster.get({id: id}, function(result) {
                $scope.applicationTypeMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:applicationTypeMasterUpdate', function(event, result) {
            $scope.applicationTypeMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
