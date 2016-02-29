'use strict';

angular.module('watererpApp')
	.controller('DepartmentTypeMasterDeleteController', function($scope, $uibModalInstance, entity, DepartmentTypeMaster) {

        $scope.departmentTypeMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            DepartmentTypeMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
