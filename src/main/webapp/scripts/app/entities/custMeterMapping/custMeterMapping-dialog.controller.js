'use strict';

angular.module('watererpApp').controller('CustMeterMappingDialogController',
        function($scope, $stateParams, $http, $state, CustMeterMapping, CustDetails, MeterDetails, ApplicationTxn, ApplicationTxnService) {

        $scope.custMeterMapping = {};
        $scope.custdetailss = CustDetails.query();
        $scope.meterdetailss = MeterDetails.query();
        $scope.applicationTxn = {};
        $scope.applicationTxnId = $stateParams.applicationTxnId;
        
        $scope.load = function(id) {
            CustMeterMapping.get({id : id}, function(result) {
                $scope.custMeterMapping = result;
            });
        };
        
        if($stateParams.id != null){
        	$scope.load($stateParams);
        }
        
       /* if($stateParams.applicationTxnId != null){
        	console.log("applicationTxnId: "+$stateParams.applicationTxnId);
        	ApplicationTxn.get({id : $stateParams.applicationTxnId}, function(result) {
                $scope.applicationTxn = result;
            });	
        }*/
        
        if($stateParams.id != null){
        	$scope.load($stateParams.id);
        }

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:custMeterMappingUpdate', result);
            $scope.isSaving = false;
            //$state.go('applicationTxn');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.custMeterMapping.id != null) {
                CustMeterMapping.update($scope.custMeterMapping, onSaveSuccess, onSaveError);
            } else {
                CustMeterMapping.save($scope.custMeterMapping, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForFromDate = {};

        $scope.datePickerForFromDate.status = {
            opened: false
        };

        $scope.datePickerForFromDateOpen = function($event) {
            $scope.datePickerForFromDate.status.opened = true;
        };
        $scope.datePickerForToDate = {};

        $scope.datePickerForToDate.status = {
            opened: false
        };

        $scope.datePickerForToDateOpen = function($event) {
            $scope.datePickerForToDate.status.opened = true;
        };
        
        $scope.getLocation = function(val) {
			$scope.isValidCust = false;
			if (val != null && val.length > 2)
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
        
        $scope.onSelect = function($item, $model, $label) {
			console.log($item);
			/*var arr = $item.split("-");
			$scope.cust = {};
			$scope.collDetails.can = arr[0];
			$scope.collDetails.consName = arr[1];
			$scope.collDetails.address = arr[2];
			$scope.custInfo = "";
			$scope.isValidCust = true;*/
		};
});
