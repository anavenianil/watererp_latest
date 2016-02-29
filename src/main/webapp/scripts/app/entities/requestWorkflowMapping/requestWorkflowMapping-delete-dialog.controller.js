'use strict';

angular.module('watererpApp')
	.controller('RequestWorkflowMappingDeleteController', function($scope, $uibModalInstance, entity, RequestWorkflowMapping) {

        $scope.requestWorkflowMapping = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            RequestWorkflowMapping.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
