'use strict';

angular.module('watererpApp')
	.controller('ApplicationTxnDeleteController', function($scope, $uibModalInstance, entity, ApplicationTxn) {

        $scope.applicationTxn = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            ApplicationTxn.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
