'use strict';

angular.module('watererpApp')
	.controller('MainSewerageSizeDeleteController', function($scope, $uibModalInstance, entity, MainSewerageSize) {

        $scope.mainSewerageSize = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            MainSewerageSize.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
