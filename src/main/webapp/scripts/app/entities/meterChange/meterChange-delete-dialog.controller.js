'use strict';

angular.module('watererpApp')
	.controller('MeterChangeDeleteController', function($scope, $uibModalInstance, entity, MeterChange) {

        $scope.meterChange = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            MeterChange.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
