'use strict';

angular.module('watererpApp')
    .controller('CreateCanDialogController', function ($scope, $state, $stateParams, ApplicationTxn, ParseLinks, DateUtils,
    		ApplicationTxnService, GetFeasibilityStudy, GetProceedings) {

    	$scope.applicationTxn = {};
    	$scope.proceedings = {};
    	$scope.proceedings.divisionMaster = {};
    	$scope.proceedings.streetMaster = {};
    	$scope.divisionCode = '';
		$scope.streetNo = '';

    	$scope.dtmax = new Date();
    	
    	$scope.load = function(id){
    		ApplicationTxn.get({id : id}, function(result) {
                $scope.applicationTxn = result;
            });
    	}
    	
    	GetFeasibilityStudy.get({applicationTxnId : $stateParams.applicationTxnId}, function(result){
    		$scope.feasibilityStudy = result;
    	});
    	
    	GetProceedings.get({applicationTxnId : $stateParams.applicationTxnId}, function(result){
    		$scope.proceedings = result;
    	});
    	
    	if($stateParams.id != null){
               $scope.load($stateParams.id); 
        }
    	
    	if($stateParams.applicationTxnId != null){
            $scope.load($stateParams.applicationTxnId); 
    	}
    	
    	/*$scope.canGenerate = function(feasibilityId){
	    	ApplicationTxnService.generateCan({feasibilityId : feasibilityId}, function(result){
	    		$scope.canNo = result;
	    	});
    	}*/
    	
        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:applicationTxnUpdate', result);
            $scope.isSaving = false;
            $state.go('applicationTxn');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
        	ApplicationTxnService.approveRequest($scope.applicationTxn.id, $scope.applicationTxn.remarks);
            $scope.isSaving = true;
            if ($scope.applicationTxn.id != null) {
                ApplicationTxn.update($scope.applicationTxn, onSaveSuccess, onSaveError);
            } else {
                //ApplicationTxn.save($scope.applicationTxn, onSaveSuccess, onSaveError);
            	alert("Not Saved");
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
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
    });




