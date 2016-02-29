'use strict';

angular.module('watererpApp')
	.controller('WorkflowMasterDeleteController', function($scope, $uibModalInstance, entity, WorkflowMaster) {

        $scope.workflowMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            WorkflowMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
