'use strict';

angular.module('watererpApp')
	.controller('RoleWorkflowMappingDeleteController', function($scope, $uibModalInstance, entity, RoleWorkflowMapping) {

        $scope.roleWorkflowMapping = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            RoleWorkflowMapping.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
