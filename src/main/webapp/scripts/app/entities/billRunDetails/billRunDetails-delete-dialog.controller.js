'use strict';

angular.module('watererpApp')
	.controller('BillRunDetailsDeleteController', function($scope, $uibModalInstance, entity, BillRunDetails) {

        $scope.billRunDetails = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            BillRunDetails.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
