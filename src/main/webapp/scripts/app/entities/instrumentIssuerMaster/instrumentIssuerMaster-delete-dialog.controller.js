'use strict';

angular.module('watererpApp')
	.controller('BankMasterDeleteController', function($scope, $uibModalInstance, entity, BankMaster) {

        $scope.instrumentIssuerMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            BankMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
