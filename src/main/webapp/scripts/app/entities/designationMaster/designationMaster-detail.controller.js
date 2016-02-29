'use strict';

angular.module('watererpApp')
    .controller('DesignationMasterDetailController', function ($scope, $rootScope, $stateParams, entity, DesignationMaster, StatusMaster) {
        $scope.designationMaster = entity;
        $scope.load = function (id) {
            DesignationMaster.get({id: id}, function(result) {
                $scope.designationMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:designationMasterUpdate', function(event, result) {
            $scope.designationMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
