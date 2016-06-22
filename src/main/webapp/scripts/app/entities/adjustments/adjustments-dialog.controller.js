'use strict';

angular.module('watererpApp').controller(
		'AdjustmentsDialogController',
		function($scope, $http, $stateParams, Adjustments, CustDetails,
				BillFullDetails, TransactionTypeMaster, User,
				CustomerComplaints, $state) {

			$scope.adjustments = {};
			$scope.billfulldetailss = BillFullDetails.query();
			$scope.transactiontypemasters = TransactionTypeMaster.query();
			//$scope.transactiontypemasters=" ";
			$scope.load = function(id) {
				Adjustments.get({
					id : id
				}, function(result) {
					$scope.adjustments = result;
				});
			};

			if ($stateParams.id != null) {
				$scope.load($stateParams.id)
			}

			//to search CAN
			$scope.getCustInfo = function(val) {
				$scope.isValidCust = false;
				return $http.get('api/custDetailss/searchCAN/' + val).then(function(response) {
					var res = response.data.map(function(item) {
						return item;
					});

					return res;
				});
			}
			//when selected searched CAN in DropDown
			$scope.onSelect = function($item, $model, $label) {
				var arr = $item.split("-");
				$scope.adjustments = {};
				$scope.adjustments.can = arr[0].trim();
				$scope.adjustments.name = arr[1];
				$scope.adjustments.address = arr[2];
				$scope.adjustments.custInfo = arr[0] + " | " + arr[1];
				$scope.adjustInfo = "";
				$scope.isValidCust = true;
                $scope.rc.editForm.attempted=false;
                $scope.editForm.$setPristine();
                
				CustomerComplaints.getByCan({can:$scope.adjustments.can, status: 5}, function(
						data) {
					$scope.customerComplaintss = data;
				});
			};

			var onSaveSuccess = function(result) {
				$scope.$emit('watererpApp:adjustmentsUpdate', result);
				//$uibModalInstance.close(result);
				$scope.isSaving = false;
				$scope.adjustments.id = result.id;
				$('#saveSuccessfullyModal').modal('show');
				//$state.go('adjustments');
				$scope.rc.editForm.attempted = false;
				$scope.editForm.$setPristine();
				//$scope.clear();
			};

			var onSaveError = function(result) {
				$scope.isSaving = false;
			};

			$scope.save = function() {
				$scope.isSaving = true;
				if ($scope.adjustments.id != null) {
					Adjustments.update($scope.adjustments, onSaveSuccess,
							onSaveError);
				} else {
					Adjustments.save($scope.adjustments, onSaveSuccess,
							onSaveError);
				}
			};

			$scope.clear = function() {
				//$uibModalInstance.dismiss('cancel');
				$scope.adjustments = {
					can : null,
					amount : null,
					user : null,
					txnTime : null,
					status : null,
					transactionTypeMaster : null,
					customerComplaints : null,
					remarks : null
				};
				$scope.rc.editForm.attempted=false;
                $scope.editForm.$setPristine();
			};
			$scope.datePickerForTxnTime = {};

			$scope.datePickerForTxnTime.status = {
				opened : false
			};

			$scope.datePickerForTxnTimeOpen = function($event) {
				$scope.datePickerForTxnTime.status.opened = true;
			};
		});
