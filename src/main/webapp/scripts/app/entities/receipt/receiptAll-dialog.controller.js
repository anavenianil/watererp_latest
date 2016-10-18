'use strict';

angular.module('watererpApp').controller('ReceiptAllDialogController',
        function($scope, $stateParams, $state, Receipt, PaymentTypes, SewerageRequest, Proceedings) {

        $scope.receipt = {};
        $scope.paymenttypess = PaymentTypes.query();
        $scope.collectionTypeMasters = [{"collName":"New Connection"},{"collName":"Name Change"},{"collName":"Sewerage Fee"}];
        
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
        
    	$scope.getReceiptAmount = function(requestType, requestId){
    		console.log(requestType);
    		console.log(requestId);
    		if(requestType == "Sewerage Fee"){
    			SewerageRequest.get({id : requestId}, function(result) {
                    $scope.sewerageRequest = result;
                    $scope.receipt.amount = $scope.sewerageRequest.amount;
                });
    		}else if(requestType == "New Connection"){
    			var onGetingProceedings = function (result) {
    				$scope.proceedings = result;
                    $scope.receipt.amount = $scope.proceedings.grandTotal;
    	        };
    			Proceedings.getByApplicationTxn({applicationTxnId : requestId}, onGetingProceedings);/*, function(result) {
                    $scope.proceedings = result;
                    $scope.receipt.amount = $scope.proceedings.grandTotal;
                });*/
    		}
    	}
    	
    	
    	
    	
});
