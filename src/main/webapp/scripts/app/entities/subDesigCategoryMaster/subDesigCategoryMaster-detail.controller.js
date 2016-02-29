'use strict';

angular.module('watererpApp')
    .controller('SubDesigCategoryMasterDetailController', function ($scope, $rootScope, $stateParams, entity, SubDesigCategoryMaster, StatusMaster) {
        $scope.subDesigCategoryMaster = entity;
        $scope.load = function (id) {
            SubDesigCategoryMaster.get({id: id}, function(result) {
                $scope.subDesigCategoryMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:subDesigCategoryMasterUpdate', function(event, result) {
            $scope.subDesigCategoryMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
