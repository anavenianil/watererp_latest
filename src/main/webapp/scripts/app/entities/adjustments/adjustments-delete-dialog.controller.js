'use strict';

angular.module('watererpApp')
	.controller('AdjustmentsDeleteController', function($scope, $uibModalInstance, entity, Adjustments) {

        $scope.adjustments = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Adjustments.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
