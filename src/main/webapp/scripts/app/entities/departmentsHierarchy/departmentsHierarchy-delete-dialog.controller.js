'use strict';

angular.module('watererpApp')
	.controller('DepartmentsHierarchyDeleteController', function($scope, $uibModalInstance, entity, DepartmentsHierarchy) {

        $scope.departmentsHierarchy = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            DepartmentsHierarchy.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
