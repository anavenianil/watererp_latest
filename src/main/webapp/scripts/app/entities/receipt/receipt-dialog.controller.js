'use strict';

angular.module('watererpApp').controller('ReceiptDialogController',
        function($scope, $stateParams, $state, Receipt, ApplicationTxn, PaymentTypes, ApplicationTxnService, ProceedingsService) {

        $scope.receipt = {};
        $scope.applicationtxns = ApplicationTxn.query();
        $scope.paymenttypess = PaymentTypes.query();
        
        $scope.maxDate = new Date();
        $scope.receipt.receiptDate = new Date();
        
        $scope.load = function(id) {
            Receipt.get({id : id}, function(result) {
                $scope.receipt = result;
            });
        };

        if($stateParams.id != null){
        	$scope.load($stateParams.id);
        }
        
        if($stateParams.applicationTxnId != null){
        	//alert($stateParams.applicationTxnId);
        	ProceedingsService.get({applicationTxnId: $stateParams.applicationTxnId}, function(result) {
                $scope.proceedings = result;
                $scope.receipt.applicationTxn = $scope.proceedings.applicationTxn; 
                $scope.receipt.amount = $scope.proceedings.grandTotal;
            });
        }
        
        
        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:receiptUpdate', result);
            $scope.isSaving = false;
            $state.go('applicationTxn');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
        	ApplicationTxnService.approveRequest($scope.receipt.applicationTxn.id, $scope.receipt.remarks);
            $scope.isSaving = true;
            if ($scope.receipt.id != null) {
                Receipt.update($scope.receipt, onSaveSuccess, onSaveError);
            } else {
                Receipt.save($scope.receipt, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForCheckOrDdDate = {};

        $scope.datePickerForCheckOrDdDate.status = {
            opened: false
        };

        $scope.datePickerForCheckOrDdDateOpen = function($event) {
            $scope.datePickerForCheckOrDdDate.status.opened = true;
        };
        $scope.datePickerForReceiptDate = {};

        $scope.datePickerForReceiptDate.status = {
            opened: false
        };

        $scope.datePickerForReceiptDateOpen = function($event) {
            $scope.datePickerForReceiptDate.status.opened = true;
        };
});
