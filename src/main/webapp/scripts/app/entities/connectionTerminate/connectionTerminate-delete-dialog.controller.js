'use strict';

angular.module('watererpApp')
	.controller('ConnectionTerminateDeleteController', function($scope, $uibModalInstance, entity, ConnectionTerminate) {

        $scope.connectionTerminate = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            ConnectionTerminate.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
