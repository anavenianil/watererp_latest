'use strict';

angular.module('watererpApp')
	.controller('GroupMasterDeleteController', function($scope, $uibModalInstance, entity, GroupMaster) {

        $scope.groupMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            GroupMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
