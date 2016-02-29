'use strict';

angular.module('watererpApp')
	.controller('ReqDesigWorkflowMappingDeleteController', function($scope, $uibModalInstance, entity, ReqDesigWorkflowMapping) {

        $scope.reqDesigWorkflowMapping = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            ReqDesigWorkflowMapping.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
