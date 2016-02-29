'use strict';

angular.module('watererpApp')
	.controller('WorkflowTxnDetailsDeleteController', function($scope, $uibModalInstance, entity, WorkflowTxnDetails) {

        $scope.workflowTxnDetails = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            WorkflowTxnDetails.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
