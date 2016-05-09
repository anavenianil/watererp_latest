'use strict';

angular.module('watererpApp').controller('CustomerPipeSizeChangeController',
   /* ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'CustDetails', 'TariffCategoryMaster',*/
        function($scope, $stateParams, /*$uibModalInstance, entity,*/ CustDetails, TariffCategoryMaster, $state, $http, CustDetailsSearchCAN,
        		WorkflowTxnDetails, PipeSizeMaster, Customer, GetPipeSizeDetail) {

        //$scope.custDetails = entity;
		$scope.custDetails = {};
        $scope.pipesizemasters = PipeSizeMaster.query();
        //$scope.custDetails.pipeSizeMaster = {};
        $scope.workflowTxnDetails = {};
        $scope.customer = {};
        $scope.customer.changeType = "PIPESIZE";
        
        $scope.dtmax = new Date();
        
        if($stateParams.id != null){
        	$scope.load($stateParams.id);
        }

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:custDetailsUpdate', result);
            //$uibModalInstance.close(result);
            $scope.isSaving = false;
            //$state.go('customer');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.customer.id != null) {
            	Customer.update($scope.customer, onSaveSuccess, onSaveError);
            } else {
            	Customer.save($scope.customer, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            //$uibModalInstance.dismiss('cancel');
        };
        
        $scope.datePickerForRequestedDate = {};

        $scope.datePickerForRequestedDate.status = {
            opened: false
        };

        $scope.datePickerForRequestedDateOpen = function($event) {
            $scope.datePickerForRequestedDate.status.opened = true;
        };
        
        
        
        //to search CAN
        $scope.getLocation = function(val) {
			$scope.isValidCust = false;
			return $http.get('api/custDetailss/searchCAN/' + val, {
				params : {
					address : val,
					sensor : false
				}
			}).then(function(response) {
				var res = response.data.map(function(item) {
					return item;
				});

				return res;
			});
		}
        
        $scope.getCustDetails = function(can) {
			CustDetailsSearchCAN.get({can : can}, function(result) {
                $scope.custDetails = result;
                $scope.getPipeSizeDetail(result.pipeSize);
                $scope.customer.can = result.can;
                //$scope.workflowTxnDetails.previousValue = $scope.custDetails.pipeSize;
            });
        };
        
        //when selected searched CAN in DropDown
        $scope.onSelect = function($item, $model, $label) {
			//console.log($item);
			var arr = $item.split("-");
			$scope.custDetails = {};
			$scope.custDetails.can = arr[0].trim();
			$scope.custDetails.name = arr[1];
			$scope.custDetails.address = arr[2];
			$scope.getCustDetails($scope.custDetails.can);
			$scope.custInfo = ""; 
			$scope.isValidCust = true;
		};
		
		
		$scope.getPipeSizeDetail = function(pipeSize) {
			GetPipeSizeDetail.findByPipeSize(pipeSize).then(
							function(result) {
								$scope.pipeSizeMaster = result;
								$scope.customer.pipeSizeMaster = result;
							});
		};
		
		/*$scope.saveChanges = function(){
			$scope.workflowTxnDetails.requestMaster = {}
			$scope.workflowTxnDetails.requestMaster.id = 9;
			$scope.workflowTxnDetails.columnName  = "PipeSize";
			console.log("This is the data being posted to server:" + JSON.stringify($scope.workflowTxnDetails));
			WorkflowTxnDetails.save($scope.workflowTxnDetails, onSaveSuccess, onSaveError);
		}*/
		
		
		/*$scope.saveChanges1 = function(){
			console.log("This is the data being posted to server:" + JSON.stringify($scope.workflowTxnDetails));
			return $http.post('/api/workflowTxnDetailsArr',$scope.workflowTxnDetails).then(function(response) {
				console.log("Server response:" + JSON.stringify(response));
			});
		}*/
		
}/*]*/);
