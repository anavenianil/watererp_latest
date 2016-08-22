'use strict';

angular.module('watererpApp')
	.controller('ValveComplaintDeleteController', function($scope, $uibModalInstance, entity, ValveComplaint) {

        $scope.valveComplaint = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            ValveComplaint.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
