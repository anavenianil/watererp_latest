'use strict';

angular.module('watererpApp')
	.controller('OrgRoleHierarchyDeleteController', function($scope, $uibModalInstance, entity, OrgRoleHierarchy) {

        $scope.orgRoleHierarchy = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            OrgRoleHierarchy.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
