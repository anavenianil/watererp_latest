'use strict';

angular.module('watererpApp')
	.controller('BillDetailsDeleteController', function($scope, $uibModalInstance, entity, BillDetails) {

        $scope.billDetails = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            BillDetails.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
