'use strict';

angular.module('watererpApp')
	.controller('UomDeleteController', function($scope, $uibModalInstance, entity, Uom) {

        $scope.uom = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Uom.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
