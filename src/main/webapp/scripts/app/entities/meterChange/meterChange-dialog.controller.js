'use strict';

angular.module('watererpApp').controller('MeterChangeDialogController',
        function($scope, $state, $stateParams, MeterChange, CustDetails, MeterDetails, User, $http, CustDetailsSearchCAN) {

        $scope.meterChange = {};
        $scope.custdetailss = CustDetails.query();
        $scope.meterdetailss = MeterDetails.query();
        $scope.users = User.query();
        $scope.meterChangeStatuss = [{"id":1,"status":"Meter Stuck"},{"id":2,"status":"Meter Break"}];
        $scope.load = function(id) {
            MeterChange.get({id : id}, function(result) {
                $scope.meterChange = result;
            });
        };
        
        if($stateParams.id != null){
        	$scope.load($stateParams.id);
        }

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:meterChangeUpdate', result);
            //$uibModalInstance.close(result);
            $scope.isSaving = false;
            $state.go('meterChange');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.meterChange.id != null) {
                MeterChange.update($scope.meterChange, onSaveSuccess, onSaveError);
            } else {
                MeterChange.save($scope.meterChange, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForApprovedDate = {};

        $scope.datePickerForApprovedDate.status = {
            opened: false
        };

        $scope.datePickerForApprovedDateOpen = function($event) {
            $scope.datePickerForApprovedDate.status.opened = true;
        };
        
        
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
                $scope.meterChange.custDetails = $scope.custDetails;
                $scope.meterChange.existingMeterNumber = $scope.custDetails.meterNo;
                $scope.meterChange.existingMeterReading = $scope.custDetails.prevReading;
                $scope.meterChange.custDetails.id = $scope.custDetails.id;
            });
        };
        
        
        $scope.onSelect = function($item, $model, $label) {
			console.log($item);
			var arr = $item.split("-");
			$scope.meterChange = {};
			$scope.meterChange.can = arr[0].trim();
			$scope.meterChange.name = arr[1];
			$scope.meterChange.address = arr[2];
			$scope.getCustDetails($scope.meterChange.can);
			$scope.custInfo = ""; 
			$scope.isValidCust = true;
		};
		
		
       
        
});
