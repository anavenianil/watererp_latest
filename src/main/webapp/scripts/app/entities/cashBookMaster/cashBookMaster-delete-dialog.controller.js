'use strict';

angular.module('watererpApp')
	.controller('CashBookMasterDeleteController', function($scope, $uibModalInstance, entity, CashBookMaster) {

        $scope.cashBookMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            CashBookMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
