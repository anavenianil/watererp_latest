'use strict';

angular.module('watererpApp')
	.controller('OnlinePaymentOrderDeleteController', function($scope, $uibModalInstance, entity, OnlinePaymentOrder) {

        $scope.onlinePaymentOrder = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            OnlinePaymentOrder.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
