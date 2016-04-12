'use strict';

angular.module('watererpApp')
	.controller('InstrumentIssuerMasterDeleteController', function($scope, $uibModalInstance, entity, InstrumentIssuerMaster) {

        $scope.instrumentIssuerMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            InstrumentIssuerMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
