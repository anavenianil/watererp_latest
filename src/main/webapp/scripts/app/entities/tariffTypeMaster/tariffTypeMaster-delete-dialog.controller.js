'use strict';

angular.module('waterERPApp')
	.controller('TariffTypeMasterDeleteController', function($scope, $uibModalInstance, entity, TariffTypeMaster) {

        $scope.tariffTypeMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            TariffTypeMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
