'use strict';

angular.module('watererpApp')
	.controller('IdProofMasterDeleteController', function($scope, $uibModalInstance, entity, IdProofMaster) {

        $scope.idProofMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            IdProofMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
