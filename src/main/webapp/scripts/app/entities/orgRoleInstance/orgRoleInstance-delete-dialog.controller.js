'use strict';

angular.module('watererpApp')
	.controller('OrgRoleInstanceDeleteController', function($scope, $uibModalInstance, entity, OrgRoleInstance) {

        $scope.orgRoleInstance = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            OrgRoleInstance.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
