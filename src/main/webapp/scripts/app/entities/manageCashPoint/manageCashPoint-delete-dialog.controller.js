'use strict';

angular.module('watererpApp')
	.controller('ManageCashPointDeleteController', function($scope, $uibModalInstance, entity, ManageCashPoint) {

        $scope.manageCashPoint = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            ManageCashPoint.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
