'use strict';

angular.module('watererpApp').controller('NameChangeReceiptDialogController',
        function($scope, $stateParams, $state, Receipt, PaymentTypes, Customer, CustDetailsSearchCAN, $window, $http, Principal, ConfigurationDetails) {

        //$scope.receipt = {};
        $scope.changeCaseDTO = {};
        $scope.changeCaseDTO.receipt = {};
        $scope.changeCaseDTO.customer = {};
        $scope.orgRole = {};
        $scope.paymenttypess = PaymentTypes.query();
        
        $scope.maxDt = new Date();
        $scope.changeCaseDTO.receipt.receiptDate = new Date();
        
        //$scope.changeCaseDTO.receipt.amount = 1000;
        

        Principal.getOrgRole().then(function(response) {
			$scope.orgRole = response;
		});
        
     // get NAME_CHANGE_FEE from Configuration Table
        $scope.getNameChangeFee = function() {
            ConfigurationDetails.get({id : 17}, function(result) {
                $scope.configurationDetails = result;
                $scope.changeCaseDTO.receipt.amount = $scope.configurationDetails.value;
            });
        };
        $scope.getNameChangeFee();
		
        $scope.loadCustomer = function(id) {
            Customer.get({id : id}, function(result) {
                $scope.changeCaseDTO.customer = result;
                $scope.changeCaseDTO.receipt.can = result.can;
                //$scope.getCustDetails(result.can);
            });
        };
        
        if($stateParams.customerId != null){
        	$scope.loadCustomer($stateParams.customerId);
        }
        
        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:receiptUpdate', result);
            $scope.isSaving = false;
            $scope.customer.id=result.id;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

      //approve a request
		$scope.approve = function(changeCaseDTO){
        	return $http.post('/api/customers/nameChangeReceipt',
        			changeCaseDTO).then(
					function(response) {
						console.log("Server response:"
								+ JSON.stringify(response));
						//$window.history.back();
						  $("#saveSuccessfullyModal").modal("show");
					});
        }

        $scope.confirm = function() {
            //$uibModalInstance.dismiss('cancel');
        	$("#saveSuccessfullyModal").modal("hide");
        	$state.go('customer.nameChangeList');
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
        
        $scope.datePickerForApprovedDate = {};

		$scope.datePickerForApprovedDate.status = {
			opened : false
		};

		$scope.datePickerForApprovedDateOpen = function($event) {
			$scope.datePickerForApprovedDate.status.opened = true;
		};
		
		$scope.canDecline = function() {
			var ret = false;
			switch ($scope.changeCaseDTO.customer.status) {
			case 0:
				if ($scope.orgRole.id === 10)
					ret = true;
				break;
			case 1:
				if ($scope.orgRole.id === 22)
					ret = true;
				break;
			case 2:
				if ($scope.orgRole.id === 21)
					ret = true;
				break;
			default:
				break;
			}
			return ret;
		}
		
		$scope.resetInstr=function(paymentMode)
		{
		if (paymentMode.toUpperCase() === 'CASH') 
				{
				$scope.instrEnabled=false;
				$scope.changeCaseDTO.receipt.checkOrDdDate = null;
				$scope.changeCaseDTO.receipt.checkOrDdNo = null;
				$scope.changeCaseDTO.receipt.bankName = null;
				$scope.changeCaseDTO.receipt.branchName = null;
				
				}
			else
				$scope.instrEnabled=true;
			
		}
		
});
