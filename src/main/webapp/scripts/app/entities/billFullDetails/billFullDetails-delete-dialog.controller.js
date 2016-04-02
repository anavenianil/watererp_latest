'use strict';

angular.module('waterERPApp')
	.controller('BillFullDetailsDeleteController', function($scope, $uibModalInstance, entity, BillFullDetails) {

        $scope.billFullDetails = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            BillFullDetails.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
