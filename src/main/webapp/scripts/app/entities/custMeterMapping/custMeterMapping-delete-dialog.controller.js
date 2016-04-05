'use strict';

angular.module('waterERPApp')
	.controller('CustMeterMappingDeleteController', function($scope, $uibModalInstance, entity, CustMeterMapping) {

        $scope.custMeterMapping = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            CustMeterMapping.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
