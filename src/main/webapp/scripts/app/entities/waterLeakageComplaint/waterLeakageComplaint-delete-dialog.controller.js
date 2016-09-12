'use strict';

angular.module('watererpApp')
	.controller('WaterLeakageComplaintDeleteController', function($scope, $uibModalInstance, entity, WaterLeakageComplaint) {

        $scope.waterLeakageComplaint = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            WaterLeakageComplaint.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
