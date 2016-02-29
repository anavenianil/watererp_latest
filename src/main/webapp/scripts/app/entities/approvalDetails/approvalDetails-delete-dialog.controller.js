'use strict';

angular.module('watererpApp')
	.controller('ApprovalDetailsDeleteController', function($scope, $uibModalInstance, entity, ApprovalDetails) {

        $scope.approvalDetails = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            ApprovalDetails.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
