'use strict';

angular.module('watererpApp')
	.controller('CollDetailsDeleteController', function($scope, $uibModalInstance, entity, CollDetails) {

        $scope.collDetails = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            CollDetails.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
