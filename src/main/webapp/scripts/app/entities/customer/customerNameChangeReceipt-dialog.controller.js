'use strict';

angular.module('watererpApp').controller('NameChangeReceiptDialogController',
        function($scope, $stateParams, $state, Receipt, PaymentTypes, Customer, CustDetailsSearchCAN, $window, $http, Principal, ConfigurationDetails) {

        //$scope.receipt = {};
        $scope.workflowDTO = {};
        $scope.workflowDTO.receipt = {};
        $scope.workflowDTO.customer = {};
        $scope.orgRole = {};
        $scope.paymenttypess = PaymentTypes.query();
        
        $scope.maxDt = new Date();
        $scope.workflowDTO.receipt.receiptDate = new Date();
        
        //$scope.workflowDTO.receipt.amount = 1000;
        

        Principal.getOrgRole().then(function(response) {
			$scope.orgRole = response;
		});
        
     // get NAME_CHANGE_FEE from Configuration Table
        $scope.getNameChangeFee = function() {
            ConfigurationDetails.get({id : 17}, function(result) {
                $scope.configurationDetails = result;
                $scope.workflowDTO.receipt.amount = $scope.configurationDetails.value;
            });
        };
        $scope.getNameChangeFee();
		
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
			default:
				break;
			}
			return ret;
		}
});
