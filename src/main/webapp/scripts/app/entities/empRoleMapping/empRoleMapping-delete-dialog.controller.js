'use strict';

angular.module('watererpApp')
	.controller('EmpRoleMappingDeleteController', function($scope, $uibModalInstance, entity, EmpRoleMapping) {

        $scope.empRoleMapping = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            EmpRoleMapping.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
