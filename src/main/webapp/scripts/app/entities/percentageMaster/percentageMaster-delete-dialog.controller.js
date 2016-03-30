'use strict';

angular.module('watererpApp')
	.controller('PercentageMasterDeleteController', function($scope, $uibModalInstance, entity, PercentageMaster) {

        $scope.percentageMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            PercentageMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
