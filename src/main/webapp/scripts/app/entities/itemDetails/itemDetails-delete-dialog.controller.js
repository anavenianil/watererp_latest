'use strict';

angular.module('watererpApp')
	.controller('ItemDetailsDeleteController', function($scope, $uibModalInstance, entity, ItemDetails) {

        $scope.itemDetails = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            ItemDetails.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
