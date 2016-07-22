'use strict';

angular.module('watererpApp').controller('OnlinePaymentResponseDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'OnlinePaymentResponse', 'OnlinePaymentOrder',
        function($scope, $stateParams, $uibModalInstance, entity, OnlinePaymentResponse, OnlinePaymentOrder) {

        $scope.onlinePaymentResponse = entity;
        $scope.onlinepaymentorders = OnlinePaymentOrder.query();
        $scope.load = function(id) {
            OnlinePaymentResponse.get({id : id}, function(result) {
                $scope.onlinePaymentResponse = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:onlinePaymentResponseUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.onlinePaymentResponse.id != null) {
                OnlinePaymentResponse.update($scope.onlinePaymentResponse, onSaveSuccess, onSaveError);
            } else {
                OnlinePaymentResponse.save($scope.onlinePaymentResponse, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForResponseTime = {};

        $scope.datePickerForResponseTime.status = {
            opened: false
        };

        $scope.datePickerForResponseTimeOpen = function($event) {
            $scope.datePickerForResponseTime.status.opened = true;
        };
}]);
