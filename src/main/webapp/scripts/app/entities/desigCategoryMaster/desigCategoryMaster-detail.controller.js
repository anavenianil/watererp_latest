'use strict';

angular.module('watererpApp')
    .controller('DesigCategoryMasterDetailController', function ($scope, $rootScope, $stateParams, entity, DesigCategoryMaster, StatusMaster) {
        $scope.desigCategoryMaster = entity;
        $scope.load = function (id) {
            DesigCategoryMaster.get({id: id}, function(result) {
                $scope.desigCategoryMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:desigCategoryMasterUpdate', function(event, result) {
            $scope.desigCategoryMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
