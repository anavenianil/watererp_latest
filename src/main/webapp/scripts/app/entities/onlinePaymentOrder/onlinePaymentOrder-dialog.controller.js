'use strict';

angular.module('watererpApp').controller('OnlinePaymentOrderDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'OnlinePaymentOrder', 'MerchantMaster',
        function($scope, $stateParams, $window, $uibModalInstance, entity, OnlinePaymentOrder, OnlinePaymentResponseSvc, MerchantMaster) {

        $scope.onlinePaymentOrder = entity;
        $scope.merchantmasters = MerchantMaster.query();
        $scope.load = function(id) {
            OnlinePaymentOrder.get({id : id}, function(result) {
                $scope.onlinePaymentOrder = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:onlinePaymentOrderUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
            OnlinePaymentResponseSvc.findByOrder({orderId : result}, function(result) {
                $scope.onlinePaymentResponse = result;
                $window.location.href = $scope.onlinePaymentResponse.redirectUrl;
            });
            
            
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.onlinePaymentOrder.id != null) {
                OnlinePaymentOrder.update($scope.onlinePaymentOrder, onSaveSuccess, onSaveError);
            } else {
                OnlinePaymentOrder.save($scope.onlinePaymentOrder, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForOrderTime = {};

        $scope.datePickerForOrderTime.status = {
            opened: false
        };

        $scope.datePickerForOrderTimeOpen = function($event) {
            $scope.datePickerForOrderTime.status.opened = true;
        };
}]);
