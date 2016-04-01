'use strict';

angular.module('watererpApp')
    .controller('ComplaintTypeMasterDetailController', function ($scope, $rootScope, $stateParams, entity, ComplaintTypeMaster) {
        $scope.complaintTypeMaster = entity;
        $scope.load = function (id) {
            ComplaintTypeMaster.get({id: id}, function(result) {
                $scope.complaintTypeMaster = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:complaintTypeMasterUpdate', function(event, result) {
            $scope.complaintTypeMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
