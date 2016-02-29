'use strict';

angular.module('watererpApp')
	.controller('WorkflowRelationshipsDeleteController', function($scope, $uibModalInstance, entity, WorkflowRelationships) {

        $scope.workflowRelationships = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            WorkflowRelationships.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
