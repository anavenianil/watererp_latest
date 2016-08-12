'use strict';

angular.module('watererpApp')
	.controller('BurstComplaintDeleteController', function($scope, $uibModalInstance, entity, BurstComplaint) {

        $scope.burstComplaint = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            BurstComplaint.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
