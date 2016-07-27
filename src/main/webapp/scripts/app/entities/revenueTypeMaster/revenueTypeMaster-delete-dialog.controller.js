'use strict';

angular.module('watererpApp')
	.controller('RevenueTypeMasterDeleteController', function($scope, $uibModalInstance, entity, RevenueTypeMaster) {

        $scope.revenueTypeMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            RevenueTypeMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
