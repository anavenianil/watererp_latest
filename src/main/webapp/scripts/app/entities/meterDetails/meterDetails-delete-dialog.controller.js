'use strict';

angular.module('waterERPApp')
	.controller('MeterDetailsDeleteController', function($scope, $uibModalInstance, entity, MeterDetails) {

        $scope.meterDetails = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            MeterDetails.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
