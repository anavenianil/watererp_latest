'use strict';

angular.module('watererpApp')
	.controller('FeasibilityStatusDeleteController', function($scope, $uibModalInstance, entity, FeasibilityStatus) {

        $scope.feasibilityStatus = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            FeasibilityStatus.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
