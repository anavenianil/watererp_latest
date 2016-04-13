'use strict';

angular.module('watererpApp')
	.controller('MeterStatusDeleteController', function($scope, $uibModalInstance, entity, MeterStatus) {

        $scope.meterStatus = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            MeterStatus.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
