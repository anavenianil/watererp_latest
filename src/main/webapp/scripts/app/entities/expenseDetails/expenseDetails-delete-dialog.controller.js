'use strict';

angular.module('watererpApp')
	.controller('ExpenseDetailsDeleteController', function($scope, $uibModalInstance, entity, ExpenseDetails) {

        $scope.expenseDetails = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            ExpenseDetails.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
