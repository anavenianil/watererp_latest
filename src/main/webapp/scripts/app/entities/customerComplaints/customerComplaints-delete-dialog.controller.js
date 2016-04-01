'use strict';

angular.module('watererpApp')
	.controller('CustomerComplaintsDeleteController', function($scope, $uibModalInstance, entity, CustomerComplaints) {

        $scope.customerComplaints = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            CustomerComplaints.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
