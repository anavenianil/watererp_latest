'use strict';

angular.module('waterERPApp')
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
