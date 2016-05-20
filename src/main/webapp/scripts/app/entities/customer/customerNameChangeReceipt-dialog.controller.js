'use strict';

angular.module('watererpApp').controller('NameChangeReceiptDialogController',
        function($scope, $stateParams, $state, Receipt, PaymentTypes, Customer, CustDetailsSearchCAN, $window, $http, Principal) {

        //$scope.receipt = {};
        $scope.workflowDTO = {};
        $scope.workflowDTO.receipt = {};
        $scope.workflowDTO.customer = {};
        $scope.orgRole = {};
        $scope.paymenttypess = PaymentTypes.query();
        
        $scope.maxDate = new Date();
        $scope.workflowDTO.receipt.receiptDate = new Date();
        
        $scope.workflowDTO.receipt.amount = 1000;
        

        Principal.getOrgRole().then(function(response) {
			$scope.orgRole = response;
		});
        
     // get cust details by CAN
		/*$scope.getCustDetails = function(can) {
			CustDetailsSearchCAN.get({can : can},function(result) {
								$scope.custDetails = result;
							});
		};*/
		
        $scope.loadCustomer = function(id) {
            Customer.get({id : id}, function(result) {
                $scope.workflowDTO.customer = result;
                $scope.workflowDTO.receipt.can = result.can;
                //$scope.getCustDetails(result.can);
            });
        };
        
        if($stateParams.customerId != null){
        	$scope.loadCustomer($stateParams.customerId);
        }
        
        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:receiptUpdate', result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        /*$scope.save = function () {
            $scope.isSaving = true;
            if ($scope.workflowDTO.customer.id != null) {
                Customer.update($scope.customer, onSaveSuccess, onSaveError);
            }
        };*/
      //approve a request
		$scope.approve = function(workflowDTO){
        	return $http.post('/api/customers/customersApprove',
					workflowDTO).then(
					function(response) {
						console.log("Server response:"
								+ JSON.stringify(response));
						$window.history.back();
					});
        }

        $scope.clear = function() {
            //$uibModalInstance.dismiss('cancel');
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
			switch ($scope.workflowDTO.customer.status) {
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
			case 3:
				if ($scope.orgRole.orgRoleName === 'Technical Manager')
					ret = true;
				break;
			case 4:
				if ($scope.orgRole.orgRoleName === 'Assistant Accountant(Revenue)')
					ret = true;
				break;
			case 5:
				if ($scope.orgRole.orgRoleName === 'Stores & Supplies Officer')
					ret = true;
				break;
			case 6:
				if ($scope.orgRole.orgRoleName === 'Officer, Operation & Maintance - NRW, Water Supply and Sanitation')
					ret = true;
				break;
			case 7:
				if ($scope.orgRole.orgRoleName === 'Billing Officer')
					ret = true;
				break;
			case 8:
				break;
			default:
				break;

			}
			return ret;
		}
});
