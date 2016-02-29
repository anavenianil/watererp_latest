'use strict';

angular.module('watererpApp')
	.controller('StatusMasterDeleteController', function($scope, $uibModalInstance, entity, StatusMaster) {

        $scope.statusMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            StatusMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
