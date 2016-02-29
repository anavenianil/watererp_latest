'use strict';

angular.module('watererpApp')
	.controller('EmpMasterDeleteController', function($scope, $uibModalInstance, entity, EmpMaster) {

        $scope.empMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            EmpMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
