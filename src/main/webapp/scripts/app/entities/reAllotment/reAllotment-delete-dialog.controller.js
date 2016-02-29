'use strict';

angular.module('watererpApp')
	.controller('ReAllotmentDeleteController', function($scope, $uibModalInstance, entity, ReAllotment) {

        $scope.reAllotment = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            ReAllotment.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
