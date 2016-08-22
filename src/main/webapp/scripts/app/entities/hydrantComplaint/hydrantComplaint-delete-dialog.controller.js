'use strict';

angular.module('watererpApp')
	.controller('HydrantComplaintDeleteController', function($scope, $uibModalInstance, entity, HydrantComplaint) {

        $scope.hydrantComplaint = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            HydrantComplaint.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
