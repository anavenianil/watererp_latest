'use strict';

angular.module('watererpApp')
	.controller('SewerageRequestDeleteController', function($scope, $uibModalInstance, entity, SewerageRequest) {

        $scope.sewerageRequest = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            SewerageRequest.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
