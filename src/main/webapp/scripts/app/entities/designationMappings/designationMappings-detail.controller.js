'use strict';

angular.module('watererpApp')
    .controller('DesignationMappingsDetailController', function ($scope, $rootScope, $stateParams, entity, DesignationMappings, DesigCategoryMaster, SubDesigCategoryMaster, DesignationMaster, GroupMaster) {
        $scope.designationMappings = entity;
        $scope.load = function (id) {
            DesignationMappings.get({id: id}, function(result) {
                $scope.designationMappings = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:designationMappingsUpdate', function(event, result) {
            $scope.designationMappings = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
