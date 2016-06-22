'use strict';

angular.module('watererpApp').controller('MeterChangeDialogController',
        function($scope, $state, $stateParams, MeterChange, CustDetails, MeterDetails, User, $http, CustDetailsSearchCAN, ParseLinks,
        		GetMeterDetails, Principal, GetActiveCAN) {

        $scope.meterChange = {};
        $scope.custdetailss = CustDetails.query();
        //$scope.meterdetailss = MeterDetails.query();
        $scope.users = User.query();
        $scope.orgRole = {};
		Principal.getOrgRole().then(function(response) {
			$scope.orgRole = response;
		});

        $scope.meterChangeStatuss = [{"id":1,"status":"Meter Stuck"},{"id":2,"status":"Meter Break"}];
        
        $scope.CustDetailsId;
                $scope.load = function(id) {
            MeterChange.get({id : id}, function(result) {
                $scope.meterChange = result;
                $scope.meterChange.remarks = "";
            });
        };
        
        $scope.loadAllUnallottedMeter = function() {
    		$scope.meterdetailss = [];
            MeterDetails.query({page: $scope.page, size: 20, meterStatusId: 2}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.meterdetailss.push(result[i]);
                }
            });
        };
        $scope.loadAllUnallottedMeter();
        
        if($stateParams.id != null){
        	$scope.load($stateParams.id);
        }

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:meterChangeUpdate', result);
            $scope.isSaving = false;
            $scope.meterChange.id  = result.id;
            $('#saveSuccessfullyModal').modal('show');
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
        	$('#saveSuccessfullyModal').modal('hide');
        	$state.go('meterChange');
        
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
        
		$scope.prevMeterDetailss = [];
        $scope.getCustDetails = function(can) {
			CustDetailsSearchCAN.get({can : can}, function(result) {
				$scope.message = null;
                $scope.custDetails = result;
                $scope.meterChange.custDetails = $scope.custDetails;
                $scope.meterChange.prevMeterReading = $scope.custDetails.prevReading;
                $scope.custDetailsId = $scope.custDetails.id;
                $scope.meterChange.custDetails.id = $scope.custDetailsId;
                $scope.prevMeterDetailss.push($scope.custDetails.meterDetails);
                $scope.meterChange.prevMeterNo = $scope.custDetails.meterDetails;   
                if($scope.custDetails.meterDetails == null){
                	$scope.message = "Meter can't be changed for Unmetered connection for the can: "+can;
                }
            });
        };
        
      //to get active can
		$scope.getActiveCAN = function(can) {
			GetActiveCAN.findByCan({
				can : can
			}).then(function(result) {
				if (result != null && result !== '') {
					$scope.isSaving = true;
					$scope.message = "Meter change request already submitted for the the CAN: "+can;
				}
				else{
					$scope.getCustDetails(can);
					$scope.isSaving = false;
				}
					
			});
		}
		
		/*$scope.clear=function()
		{
			$scope.meterChange={presentReading:null , reasonForChange:null
					};		
		};*/
        
        
        $scope.onSelect = function($item, $model, $label) {
			console.log($item);
			var arr = $item.split("-");
			$scope.meterChange = {};
			$scope.meterChange.can = arr[0].trim();
			$scope.meterChange.name = arr[1];
			$scope.meterChange.address = arr[2];
			$scope.getActiveCAN($scope.meterChange.can);
			$scope.custInfo = ""; 
			$scope.isValidCust = true;
			$scope.rc.editForm.attempted=false;
			$scope.editForm.$setPristine();
			
		};
		
		$scope.getReason = function(status){
			$scope.meterChange.reasonForChange = status;
		}
		
		$scope.canShow = function() {
			var ret = false;
			switch ($scope.meterChange.status) {
			case 0:
				if ($scope.orgRole.id === 20)
					ret = true;
				break;
			case 1:
				if ($scope.orgRole.id === 15)
					ret = true;
				break;
			default:
				break;

			}
			return ret;
		}
		
		$scope.checkReading = function(prvReading, newReading){
			if(prvReading >= newReading)
				{
				$scope.editForm.presentReading.$setValidity(
						"ltPrevious", true);
				return true;
				}
			else
				{
				$scope.editForm.presentReading.$setValidity(
						"ltPrevious", false);
				return false;
				}
		}
});
