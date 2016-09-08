'use strict';

angular.module('watererpApp').controller(
		'applicationTxnCloseWithoutMeterDialogController',
		function($scope, $http, $state, $stateParams, ApplicationTxn,
				ParseLinks, DateUtils, ApplicationTxnService,
				GetFeasibilityStudy, GetProceedings) {

			$scope.applicationTxn = {};
			$scope.proceedings = {};
			$scope.proceedings.divisionMaster = {};
			$scope.proceedings.streetMaster = {};
			$scope.divisionCode = '';
			$scope.streetNo = '';
			$scope.applicationTxn.can = "";

			$scope.dtmax = new Date();

			$scope.load = function(id) {
				ApplicationTxn.get({
					id : id
				}, function(result) {
					$scope.applicationTxn = result;
					$scope.applicationTxn.remarks = '';
					ApplicationTxnService.generateCanWithoutMeter(id).then(function(response) {
	    				$scope.applicationTxn.can = response;
	    			});
				});
			}

			

			if ($stateParams.id != null) {
				$scope.load($stateParams.id);
			}

			if ($stateParams.applicationTxnId != null) {
				$scope.load($stateParams.applicationTxnId);
			}

	

			var onSaveSuccess = function(result) {
				$scope.$emit('watererpApp:applicationTxnUpdate', result);
				$scope.isSaving = false;
				//$state.go('applicationTxn');
				$('#saveSuccessfullyModal').modal('show');
				$scope.applicationTxnId = result.id;
				$scope.can = result.can;
			};

			var onSaveError = function(result) {
				$scope.isSaving = false;
			};

			$scope.save = function() {
				$scope.isSaving = true;
				if ($scope.applicationTxn.id != null) {
					ApplicationTxn.update($scope.applicationTxn, onSaveSuccess,	onSaveError);
				} else {
					alert("Not Saved");
				}
			};
			
			$scope.done = function(){
				$('#saveSuccessfullyModal').modal('hide');
				$state.go('applicationTxn');
			}
			

			$scope.clear = function() {
				$uibModalInstance.dismiss('cancel');
			};
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
			
			$scope.getEffectiveMonth = function(date){
				if(date != null){
					var year = date.getFullYear();
					var month = date.getMonth()+1;
					var day = date.getDate();
					if(month==1){
						year-=1;
					}
					month-=1;
					if(month==0){
						month=12;
					}
					if(month>0 && month<10){
						month="0"+month;
					}
					$scope.eDate = year+"-"+month+"-"+"01";
				}
				else{
					$scope.eDate = null;
				}
			}
			
			 $(document).ready(function() {
			        function disableBack() { window.history.forward() }
			        window.onload = disableBack();
			        window.onpageshow = function(evt) { if (evt.persisted) disableBack() }
			    });
			
		});
