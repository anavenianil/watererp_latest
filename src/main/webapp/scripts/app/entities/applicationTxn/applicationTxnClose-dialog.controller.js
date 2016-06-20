'use strict';

angular.module('watererpApp').controller(
		'applicationTxnCloseDialogController',
		function($scope, $http, $state, $stateParams, ApplicationTxn,
				ParseLinks, DateUtils, ApplicationTxnService,
				GetFeasibilityStudy, GetProceedings) {

			$scope.workflowDTO = {};
			$scope.workflowDTO.applicationTxn = {};
			$scope.workflowDTO.proceedings = {};
			$scope.workflowDTO.proceedings.divisionMaster = {};
			$scope.workflowDTO.proceedings.streetMaster = {};
			$scope.workflowDTO.divisionCode = '';
			$scope.workflowDTO.streetNo = '';
			$scope.workflowDTO.applicationTxn.can = "";
			
			$scope.actionType = "createCAN";

			$scope.dtmax = new Date();

			$scope.load = function(id) {
				ApplicationTxn.get({
					id : id
				}, function(result) {
					$scope.workflowDTO.applicationTxn = result;
					$scope.workflowDTO.applicationTxn.remarks = '';
				});
			}

			GetFeasibilityStudy.get({
				applicationTxnId : $stateParams.applicationTxnId
			}, function(result) {
				$scope.workflowDTO.feasibilityStudy = result;
			});

			GetProceedings.get({
				applicationTxnId : $stateParams.applicationTxnId
			}, function(result) {
				$scope.workflowDTO.proceedings = result;
			});
			
			$scope.getFeasibilityByAppTxn = function(applicationTxnId){
	        	GetFeasibilityStudy.get({
	    			applicationTxnId : $stateParams.applicationTxnId
	    		}, function(result) {
	    			$scope.feasibilityStudy = result;
	    			if($scope.feasibilityStudy.id !=null){
	    			ApplicationTxnService.generateCan(result.id).then(function(response) {
	    				$scope.workflowDTO.applicationTxn.can = response;
	    			});
	    			}
	    			else{
	    				alert("feasibilityNull");
	    			}
	    		});
	        }

			if ($stateParams.id != null) {
				$scope.load($stateParams.id);
			}

			if ($stateParams.applicationTxnId != null) {
				$scope.load($stateParams.applicationTxnId);
				$scope.getFeasibilityByAppTxn($stateParams.applicationTxnId);
			}


			var onSaveSuccess = function(result) {
				$scope.$emit('watererpApp:applicationTxnUpdate', result);
				$scope.isSaving = false;
				$state.go('applicationTxn');
			};

			var onSaveError = function(result) {
				$scope.isSaving = false;
			};

			 //approve a request to create new customer
			$scope.save = function(workflowDTO){
				$scope.isSaving = true;
	        	return $http.post('/api/applicationTxns/createNewCustomer',
	        			workflowDTO).then(
						function(response) {
							$state.go('applicationTxn');
						});
	        }
			
			$scope.datePickerForConnectionDate = {};

			$scope.datePickerForConnectionDate.status = {
				opened : false
			};

			$scope.datePickerForConnectionDateOpen = function($event) {
				$scope.datePickerForConnectionDate.status.opened = true;
			};

			$scope.clear = function() {
				$scope.applicationTxn = {};
			}
			
		});
