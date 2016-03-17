'use strict';

angular.module('watererpApp')
	.controller('ItemRequiredDeleteController', function($scope, $uibModalInstance, entity, ItemRequired) {

        $scope.itemRequired = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            ItemRequired.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
