'use strict';

angular.module('watererpApp')
		    .controller(
				'IssueMeterDialogController',
				function($scope, $state, $stateParams, ApplicationTxn,
						ParseLinks, DateUtils, MeterDetails,
						ApplicationTxnService, $http, $window) {

    	$scope.changeCaseDTO = {};
    	$scope.changeCaseDTO.applicationTxn = {};
    	$scope.changeCaseDTO.actionType = "issueMeter";
    	//$scope.applicationTxn = {};
    	//$scope.meterdetailss = MeterDetails.query();
    	$scope.changeCaseDTO.applicationTxn.connectionDate = new Date();
    	$scope.dtmax = new Date();
    	
    	
    	
    	$scope.loadAllUnallottedMeter = function() {
    		$scope.meterdetailss = [];
            MeterDetails.query({page: $scope.page, size: 20, meterStatusId: 2}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.meterdetailss.push(result[i]);
                }
                if($scope.meterdetailss.length == 0){
                	//alert("No Meter Available");
                	$('#meterAvailabilityModal').modal('show');
                }
            });
        };
        $scope.loadAllUnallottedMeter();
    	
    	$scope.load = function(id){
    		ApplicationTxn.get({id : id}, function(result) {
                $scope.changeCaseDTO.applicationTxn = result;
                $scope.changeCaseDTO.applicationTxn.remarks = "";
            });
    	}
    	if($stateParams.id != null){
               $scope.load($stateParams.id); 
         }
    	
    	if($stateParams.applicationTxnId != null){
            $scope.load($stateParams.applicationTxnId);
    	}
 	
    	
    	
        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:applicationTxnUpdate', result);
            $scope.isSaving = false;
            $state.go('applicationTxn');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        
      //approve a request
		$scope.save = function(changeCaseDTO){
			$scope.isSaving = true;
        	return $http.post('/api/applicationTxns/issueMeter',
        			changeCaseDTO).then(
					function(response) {
						$state.go('applicationTxn');
					});
        }
		

        $scope.clear = function() {
            //$uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForConnectionDate = {};

        $scope.datePickerForConnectionDate.status = {
            opened: false
        };

        $scope.datePickerForConnectionDateOpen = function($event) {
            $scope.datePickerForConnectionDate.status.opened = true;
        };
        
        $scope.clear = function(){
        	$scope.applicationTxn = {
            };
        }
        
        $scope.closeDialague = function(){
        	$('#meterAvailabilityModal').modal('hide');
        	//$window.history.back();
        }
        
        $(document).ready(function() {
  	        function disableBack() { window.history.forward() }
  	        window.onload = disableBack();
  	        window.onpageshow = function(evt) { if (evt.persisted) disableBack() }
  	    });
        
        
    });




