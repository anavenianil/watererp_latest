'use strict';

angular.module('watererpApp').controller('ReceiptDialogController',
        function($scope, $stateParams, $state, Receipt, ApplicationTxn, PaymentTypes, ApplicationTxnService, ProceedingsService, 
        		GetFeasibilityStudy, GetProceedings) {

        $scope.receipt = {};
        $scope.applicationtxns = ApplicationTxn.query();
        $scope.paymenttypess = PaymentTypes.query();
        $scope.applicationTxn = {};
        $scope.receipt.applicationTxn = {};
        
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
        
        

        $scope.getAppTxn = function(applicationTxnId){
        	 ApplicationTxn.get({id : $stateParams.applicationTxnId}, function(result) {
                 $scope.applicationTxn = result;
                 $scope.receipt.applicationTxn.remarks = "";
             });
        }
       
            
        $scope.getProceedingsByAppTxn = function(applicationTxnId){
        	GetProceedings.get({
    			applicationTxnId : $stateParams.applicationTxnId
    		}, function(result) {
    			$scope.proceedings = result;
    			$scope.receipt.applicationTxn = $scope.proceedings.applicationTxn; 
                $scope.receipt.amount = $scope.proceedings.grandTotal;
                $scope.receipt.applicationTxn.remarks = "";
    		});
        }
        
        
        if($stateParams.applicationTxnId != null){
        	$scope.getAppTxn($stateParams.applicationTxn);
        	$scope.getProceedingsByAppTxn($stateParams.applicationTxn);
        	$scope.receipt.applicationTxn.remarks = "";
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
        
        
});
