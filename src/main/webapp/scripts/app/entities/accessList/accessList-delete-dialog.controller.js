'use strict';

angular.module('watererpApp')
	.controller('AccessListDeleteController', function($scope, $uibModalInstance, entity, AccessList) {

        $scope.accessList = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            AccessList.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
