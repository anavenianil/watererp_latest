'use strict';

angular.module('watererpApp')
	.controller('ReversalDetailsDeleteController', function($scope, $uibModalInstance, entity, ReversalDetails) {

        $scope.reversalDetails = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            ReversalDetails.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
