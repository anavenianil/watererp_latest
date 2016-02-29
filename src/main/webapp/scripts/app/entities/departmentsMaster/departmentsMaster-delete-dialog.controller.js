'use strict';

angular.module('watererpApp')
	.controller('DepartmentsMasterDeleteController', function($scope, $uibModalInstance, entity, DepartmentsMaster) {

        $scope.departmentsMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            DepartmentsMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
