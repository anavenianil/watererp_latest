'use strict';

angular.module('watererpApp')
	.controller('MainWaterSizeDeleteController', function($scope, $uibModalInstance, entity, MainWaterSize) {

        $scope.mainWaterSize = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            MainWaterSize.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
