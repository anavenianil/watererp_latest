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
				$state.go('applicationTxn');
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
			
		});
