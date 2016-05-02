'use strict';

angular.module('watererpApp')
	.controller('OnlinePaymentResponseDeleteController', function($scope, $uibModalInstance, entity, OnlinePaymentResponse) {

        $scope.onlinePaymentResponse = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            OnlinePaymentResponse.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
