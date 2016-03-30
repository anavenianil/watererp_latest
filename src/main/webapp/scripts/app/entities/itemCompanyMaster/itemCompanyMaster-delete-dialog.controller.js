'use strict';

angular.module('watererpApp')
	.controller('ItemCompanyMasterDeleteController', function($scope, $uibModalInstance, entity, ItemCompanyMaster) {

        $scope.itemCompanyMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            ItemCompanyMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
