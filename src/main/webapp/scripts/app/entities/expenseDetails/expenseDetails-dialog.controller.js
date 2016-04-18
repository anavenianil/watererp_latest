'use strict';

angular.module('watererpApp')
    .controller('ExpenseDetailsDialogController', 
    		function ($scope, $state, ExpenseDetails, PaymentTypes, InstrumentIssuerMaster, CollectionTypeMaster, ParseLinks, $stateParams) {
    	$scope.expenseDetailsId = $stateParams.id;
    	$scope.load = function(id) {
            ExpenseDetails.get({id : id}, function(result) {
                $scope.expenseDetails = result;
            });
        };
        if($scope.expenseDetailsId!=null)
        $scope.load($scope.expenseDetailsId);       
    	
    	var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:expenseDetailsUpdate', result);
            $scope.isSaving = false;
            $state.go('expenseDetails');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.expenseDetails.id != null) {
                ExpenseDetails.update($scope.expenseDetails, onSaveSuccess, onSaveError);
            } else {
                ExpenseDetails.save($scope.expenseDetails, onSaveSuccess, onSaveError);
            }
        };
        
    	
    	$scope.paymenttypess = PaymentTypes.query();
        $scope.instrumentissuermasters = InstrumentIssuerMaster.query();
        $scope.collectiontypemasters = CollectionTypeMaster.query();
        
        $scope.datePickerForExpenseDt = {};

        $scope.datePickerForExpenseDt.status = {
            opened: false
        };

        $scope.datePickerForExpenseDtOpen = function($event) {
            $scope.datePickerForExpenseDt.status.opened = true;
        };
        $scope.datePickerForInstrDt = {};

        $scope.datePickerForInstrDt.status = {
            opened: false
        };

        $scope.datePickerForInstrDtOpen = function($event) {
            $scope.datePickerForInstrDt.status.opened = true;
        };
        
        $scope.resetInstr = function(paymentMode) {
			if (paymentMode === 'CASH') {
				$scope.instrEnabled = false;
				$scope.collDetails.instrNo = null;
				$scope.collDetails.instrDt = null;
				$scope.collDetails.instrIssuer = null;
			} else
				$scope.instrEnabled = true;
		}
    });