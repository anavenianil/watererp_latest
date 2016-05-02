'use strict';

angular.module('watererpApp').controller('OnlinePaymentCallbackDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'OnlinePaymentCallback', 'MerchantMaster', 'OnlinePaymentOrder',
        function($scope, $stateParams, $uibModalInstance, entity, OnlinePaymentCallback, MerchantMaster, OnlinePaymentOrder) {

        $scope.onlinePaymentCallback = entity;
        $scope.merchantmasters = MerchantMaster.query();
        $scope.onlinepaymentorders = OnlinePaymentOrder.query();
        $scope.load = function(id) {
            OnlinePaymentCallback.get({id : id}, function(result) {
                $scope.onlinePaymentCallback = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:onlinePaymentCallbackUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.onlinePaymentCallback.id != null) {
                OnlinePaymentCallback.update($scope.onlinePaymentCallback, onSaveSuccess, onSaveError);
            } else {
                OnlinePaymentCallback.save($scope.onlinePaymentCallback, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
