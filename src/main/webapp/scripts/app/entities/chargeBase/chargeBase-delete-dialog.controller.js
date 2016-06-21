'use strict';

angular.module('watererpApp')
	.controller('ChargeBaseDeleteController', function($scope, $uibModalInstance, entity, ChargeBase) {

        $scope.chargeBase = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            ChargeBase.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
