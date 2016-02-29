'use strict';

angular.module('watererpApp')
    .controller('WorkflowTxnDetailsDetailController', function ($scope, $rootScope, $stateParams, entity, WorkflowTxnDetails, RequestMaster) {
        $scope.workflowTxnDetails = entity;
        $scope.load = function (id) {
            WorkflowTxnDetails.get({id: id}, function(result) {
                $scope.workflowTxnDetails = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:workflowTxnDetailsUpdate', function(event, result) {
            $scope.workflowTxnDetails = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
