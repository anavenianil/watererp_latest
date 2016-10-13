'use strict';

angular.module('watererpApp').controller('ConnectionTerminateDialogController',
        function($scope, $stateParams, /*$uibModalInstance, entity,*/ ConnectionTerminate, MeterDetails, $http, CustDetailsSearchCAN, 
        		ParseLinks, /*GetMeterDetails,*/ $state, StreetMaster, DivisionMaster, CustDetails) {

        $scope.connectionTerminate = {};
        $scope.custDetails = {};
        $scope.maxDt = new Date();
        $scope.divisionmasters = DivisionMaster.query();
        $scope.streetMasters = StreetMaster.query();
        
        //$scope.balance = CustDetails.getByCan({can:'A0100111'});
        
        $scope.load = function(id) {
            ConnectionTerminate.get({id : id}, function(result) {
                $scope.connectionTerminate = result;
            });
        };
        
        $scope.connectionTerminate.requestDate = new Date();  
        	
        if($stateParams.id != null){
        	$scope.load($stateParams.id);
        }
        
        /*$scope.getMeterDetail = function(meterId){
        	$scope.meterdetailss = [];
        	GetMeterDetails.findByMeterId(meterId).then(function(result) {
                $scope.meterdetailss.push(result);
                $scope.connectionTerminate.meterDetails = result;
            });
    	}*/
       

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:connectionTerminateUpdate', result);
            //$uibModalInstance.close(result);
            $scope.isSaving = false;
            $state.go('connectionTerminate');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.connectionTerminate.id != null) {
                ConnectionTerminate.update($scope.connectionTerminate, onSaveSuccess, onSaveError);
            } else {
                ConnectionTerminate.save($scope.connectionTerminate, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
           // $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForRequestDate = {};

        $scope.datePickerForRequestDate.status = {
            opened: false
        };

        $scope.datePickerForRequestDateOpen = function($event) {
            $scope.datePickerForRequestDate.status.opened = true;
        };
        $scope.datePickerForMeterRecoveredDate = {};

        $scope.datePickerForMeterRecoveredDate.status = {
            opened: false
        };

        $scope.datePickerForMeterRecoveredDateOpen = function($event) {
            $scope.datePickerForMeterRecoveredDate.status.opened = true;
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
        
        
        $scope.meterdetailss = [];
        $scope.getCustDetails = function(can) {
			CustDetailsSearchCAN.get({can : can}, function(result) {
                $scope.custDetails = result;
                $scope.connectionTerminate.can = $scope.custDetails.can; 
                $scope.meterdetailss.push($scope.custDetails.meterDetails);
                $scope.connectionTerminate.meterDetails = $scope.custDetails.meterDetails;
                if($scope.custDetails.arrears > 0){
                	//$scope.isSaving = true;
                	$scope.arrearsMessage = "Clear Due Amount: "+$scope.custDetails.arrears+" Shilling(TZS)";
                }
                else{
                	$scope.isSaving = false;
                	$scope.arrearsMessage = null;
                }
            });
        };
        
        //when selected searched CAN in DropDown
        $scope.onSelect = function($item, $model, $label) {
			console.log($item);
			var arr = $item.split("-");
			$scope.custDetails = {};
			$scope.custDetails.can = arr[0].trim();
			$scope.custDetails.name = arr[1];
			$scope.custDetails.address = arr[2];
			$scope.getCustDetails($scope.custDetails.can);
			$scope.custInfo = ""; 
			$scope.isValidCust = true;
		};
});
