'use strict';

angular.module('watererpApp')
	.controller('WorkflowRelationsDeleteController', function($scope, $uibModalInstance, entity, WorkflowRelations) {

        $scope.workflowRelations = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            WorkflowRelations.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
