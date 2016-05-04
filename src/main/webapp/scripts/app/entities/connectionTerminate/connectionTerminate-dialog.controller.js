'use strict';

angular.module('watererpApp').controller('ConnectionTerminateDialogController',
        function($scope, $stateParams, /*$uibModalInstance, entity,*/ ConnectionTerminate, MeterDetails, $http, CustDetailsSearchCAN) {

        $scope.connectionTerminate = {};
        $scope.custDetails = {};
        $scope.meterdetailss = MeterDetails.query();
        $scope.load = function(id) {
            ConnectionTerminate.get({id : id}, function(result) {
                $scope.connectionTerminate = result;
            });
        };
        
        if($stateParams.id != null){
        	$scope.load($stateParams.id);
        }

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:connectionTerminateUpdate', result);
            //$uibModalInstance.close(result);
            $scope.isSaving = false;
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
        
        $scope.getCustDetails = function(can) {
			CustDetailsSearchCAN.get({can : can}, function(result) {
                $scope.custDetails = result;
                $scope.connectionTerminate.can = $scope.custDetails.can; 
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
