'use strict';

angular.module('watererpApp')
	.controller('TransactionTypeMasterDeleteController', function($scope, $uibModalInstance, entity, TransactionTypeMaster) {

        $scope.transactionTypeMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            TransactionTypeMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
