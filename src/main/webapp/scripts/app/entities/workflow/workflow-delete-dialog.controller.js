'use strict';

angular.module('watererpApp')
	.controller('WorkflowDeleteController', function($scope, $uibModalInstance, entity, Workflow) {

        $scope.workflow = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Workflow.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
