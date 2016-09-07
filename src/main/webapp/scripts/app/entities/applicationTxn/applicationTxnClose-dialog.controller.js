'use strict';

angular.module('watererpApp').controller(
		'applicationTxnCloseDialogController',
		function($scope, $http, $state, $stateParams, ApplicationTxn,
				ParseLinks, DateUtils, ApplicationTxnService,
				GetFeasibilityStudy, GetProceedings) {

			$scope.changeCaseDTO = {};
			$scope.changeCaseDTO.applicationTxn = {};
			$scope.changeCaseDTO.proceedings = {};
			$scope.changeCaseDTO.proceedings.divisionMaster = {};
			$scope.changeCaseDTO.proceedings.streetMaster = {};
			$scope.changeCaseDTO.divisionCode = '';
			$scope.changeCaseDTO.streetNo = '';
			$scope.changeCaseDTO.applicationTxn.can = "";
			
			$scope.actionType = "createCAN";

			$scope.dtmax = new Date();

			$scope.load = function(id) {
				ApplicationTxn.get({
					id : id
				}, function(result) {
					$scope.changeCaseDTO.applicationTxn = result;
					$scope.changeCaseDTO.applicationTxn.remarks = '';
				});
			}

			GetFeasibilityStudy.get({
				applicationTxnId : $stateParams.applicationTxnId
			}, function(result) {
				$scope.changeCaseDTO.feasibilityStudy = result;
			});

			GetProceedings.get({
				applicationTxnId : $stateParams.applicationTxnId
			}, function(result) {
				$scope.changeCaseDTO.proceedings = result;
			});
			
			$scope.getFeasibilityByAppTxn = function(applicationTxnId){
	        	GetFeasibilityStudy.get({
	    			applicationTxnId : $stateParams.applicationTxnId
	    		}, function(result) {
	    			$scope.feasibilityStudy = result;
	    			if($scope.feasibilityStudy.id !=null){
	    			ApplicationTxnService.generateCan(result.id).then(function(response) {
	    				$scope.changeCaseDTO.applicationTxn.can = response;
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
			$scope.save = function(changeCaseDTO){
				$scope.isSaving = true;
	        	return $http.post('/api/applicationTxns/createNewCustomer',
	        			changeCaseDTO).then(
						function(response) {
							$('#saveSuccessfullyModal').modal('show');
							$scope.changeCaseDTO = response.data;
							$scope.applicationTxnId = $scope.changeCaseDTO.applicationTxn.id;
							$scope.can = $scope.changeCaseDTO.applicationTxn.can;
							//$state.go('applicationTxn');
						});
	        }
			
			$scope.done = function(){
				$('#saveSuccessfullyModal').modal('hide');
				$state.go('applicationTxn');
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
			
			$(document).ready(function() {
		        function disableBack() { window.history.forward() }
		        window.onload = disableBack();
		        window.onpageshow = function(evt) { if (evt.persisted) disableBack() }
		    });
			
		});
