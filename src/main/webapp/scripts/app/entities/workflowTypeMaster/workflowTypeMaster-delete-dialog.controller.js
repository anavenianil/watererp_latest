'use strict';

angular.module('watererpApp')
	.controller('WorkflowTypeMasterDeleteController', function($scope, $uibModalInstance, entity, WorkflowTypeMaster) {

        $scope.workflowTypeMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            WorkflowTypeMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
