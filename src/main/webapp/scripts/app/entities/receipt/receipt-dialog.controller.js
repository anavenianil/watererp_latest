'use strict';

angular.module('watererpApp').controller('ReceiptDialogController',
        function($scope, $stateParams, $state, Receipt, ApplicationTxn, PaymentTypes, ApplicationTxnService, ProceedingsService, 
        		GetFeasibilityStudy, GetProceedings) {

        $scope.receipt = {};
        $scope.applicationtxns = ApplicationTxn.query();
        $scope.paymenttypess = PaymentTypes.query();
        $scope.applicationTxn = {};
        
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
        
        /*if($stateParams.applicationTxnId != null){
        	//alert($stateParams.applicationTxnId);
        	ProceedingsService.get({applicationTxnId: $stateParams.applicationTxnId}, function(result) {
                $scope.proceedings = result;
                $scope.receipt.applicationTxn = $scope.proceedings.applicationTxn; 
                $scope.receipt.amount = $scope.proceedings.grandTotal;
            });
        }*/
        

        $scope.getAppTxn = function(applicationTxnId){
        	 ApplicationTxn.get({id : $stateParams.applicationTxnId}, function(result) {
                 $scope.applicationTxn = result;
             });
        }
       
            
        $scope.getProceedingsByAppTxn = function(applicationTxnId){
        	GetProceedings.get({
    			applicationTxnId : $stateParams.applicationTxnId
    		}, function(result) {
    			$scope.proceedings = result;
    			$scope.receipt.applicationTxn = $scope.proceedings.applicationTxn; 
                $scope.receipt.amount = $scope.proceedings.grandTotal;
    		});
        }
        
        $scope.getFeasibilityByAppTxn = function(applicationTxnId){
        	GetFeasibilityStudy.get({
    			applicationTxnId : $stateParams.applicationTxnId
    		}, function(result) {
    			$scope.feasibilityStudy = result;
    			if($scope.feasibilityStudy.id !=null){
    			ApplicationTxnService.generateCan(result.id).then(function(response) {
    				$scope.receipt.applicationTxn.can = response;
    			});
    			}
    			else{
    				alert("feasibilityNull");
    			}
    		});
        }
        
        if($stateParams.applicationTxnId != null){
        	$scope.getAppTxn($stateParams.applicationTxn);
        	$scope.getProceedingsByAppTxn($stateParams.applicationTxn);
        	$scope.getFeasibilityByAppTxn($stateParams.applicationTxn);
        }
        
        
        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:receiptUpdate', result);
            $scope.isSaving = false;
            ApplicationTxnService.approveRequest($scope.receipt.applicationTxn.id, $scope.receipt.remarks);
            $state.go('applicationTxn');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            /*if ($scope.applicationTxn.id != null) {
                ApplicationTxn.update($scope.applicationTxn);
            } */
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
