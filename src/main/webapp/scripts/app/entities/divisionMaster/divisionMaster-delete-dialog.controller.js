'use strict';

angular.module('watererpApp')
	.controller('DivisionMasterDeleteController', function($scope, $uibModalInstance, entity, DivisionMaster) {

        $scope.divisionMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            DivisionMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
