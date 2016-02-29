'use strict';

angular.module('watererpApp')
	.controller('WorkflowStageMasterDeleteController', function($scope, $uibModalInstance, entity, WorkflowStageMaster) {

        $scope.workflowStageMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            WorkflowStageMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
