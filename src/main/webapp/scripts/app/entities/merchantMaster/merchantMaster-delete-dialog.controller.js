'use strict';

angular.module('watererpApp')
	.controller('MerchantMasterDeleteController', function($scope, $uibModalInstance, entity, MerchantMaster) {

        $scope.merchantMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            MerchantMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
