'use strict';

angular.module('watererpApp')
	.controller('ConnectionTypeMasterDeleteController', function($scope, $uibModalInstance, entity, ConnectionTypeMaster) {

        $scope.connectionTypeMaster = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            ConnectionTypeMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
