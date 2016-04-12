'use strict';

angular.module('watererpApp')
	.controller('BillRunMasterDeleteController', function($scope, $uibModalInstance, entity, BillRunMaster) {

        $scope.billRunMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            BillRunMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
