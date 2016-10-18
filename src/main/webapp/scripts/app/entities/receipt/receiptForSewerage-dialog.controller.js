'use strict';

angular.module('watererpApp').controller('ReceiptForSewerageDialogController',
        function($scope, $stateParams, $state, Receipt, PaymentTypes, SewerageRequest, Proceedings) {

        $scope.receipt = {};
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
        
        
        var onSaveSuccess = function (result) {
            //$scope.$emit('watererpApp:receiptUpdate', result);
            $scope.isSaving = false;
            //$state.go('applicationTxn');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };
        
        $scope.sewerageApprovalDTO = {};
        $scope.approveSewerageRequest = function (remarks) {
            $scope.isSaving = true;
            $scope.sewerageApprovalDTO.sewerageRequest = $scope.sewerageRequest;
            $scope.sewerageApprovalDTO.receipt = $scope.receipt;
            $scope.sewerageApprovalDTO.remarks = $scope.remarks;
            SewerageRequest.sewerageRequestApproval($scope.sewerageApprovalDTO, onSaveSuccess, onSaveError);
        };

        /*$scope.save = function () {
            $scope.isSaving = true;
            if ($scope.receipt.id != null) {
                Receipt.update($scope.receipt, onSaveSuccess, onSaveError);
            } else {
                Receipt.save($scope.receipt, onSaveSuccess, onSaveError);
            }
        };*/

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
        
        
    	$scope.resetInstr = function(paymentMode) {
			if (paymentMode.toUpperCase()  === 'CASH') {
				$scope.instrEnabled = false;
				$scope.receipt.checkOrDdDate = null;
				$scope.receipt.checkOrDdNo = null;
				$scope.receipt.bankName = null;
				$scope.receipt.branchName = null;
			} else
				$scope.instrEnabled = true;
		}
        
    	if($stateParams.sewerageRequestId !=null){
			SewerageRequest.get({id : $stateParams.sewerageRequestId}, function(result) {
                $scope.sewerageRequest = result;
                $scope.receipt.amount = $scope.sewerageRequest.amount;
            });
		}
    	
    	
    	
    	
});
