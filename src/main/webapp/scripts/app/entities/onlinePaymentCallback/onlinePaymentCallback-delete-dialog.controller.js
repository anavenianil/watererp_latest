'use strict';

angular.module('watererpApp')
	.controller('OnlinePaymentCallbackDeleteController', function($scope, $uibModalInstance, entity, OnlinePaymentCallback) {

        $scope.onlinePaymentCallback = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            OnlinePaymentCallback.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
