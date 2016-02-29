'use strict';

angular.module('watererpApp')
    .controller('ApprovalDetailsDetailController', function ($scope, $rootScope, $stateParams, entity, ApprovalDetails, Customer, FeasibilityStatus, DesignationMaster) {
        $scope.approvalDetails = entity;
        $scope.load = function (id) {
            ApprovalDetails.get({id: id}, function(result) {
                $scope.approvalDetails = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:approvalDetailsUpdate', function(event, result) {
            $scope.approvalDetails = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
