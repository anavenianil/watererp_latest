'use strict';

angular.module('watererpApp')
	.controller('ReqOrgWorkflowMappingDeleteController', function($scope, $uibModalInstance, entity, ReqOrgWorkflowMapping) {

        $scope.reqOrgWorkflowMapping = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            ReqOrgWorkflowMapping.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
