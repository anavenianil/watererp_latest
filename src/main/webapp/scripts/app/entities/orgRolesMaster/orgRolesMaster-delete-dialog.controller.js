'use strict';

angular.module('watererpApp')
	.controller('OrgRolesMasterDeleteController', function($scope, $uibModalInstance, entity, OrgRolesMaster) {

        $scope.orgRolesMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            OrgRolesMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
