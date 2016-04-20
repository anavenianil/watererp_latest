'use strict';

angular.module('watererpApp').controller(
		'ExpenseDetailsDialogController',
		function($scope, $state, ExpenseDetails, PaymentTypes,
				InstrumentIssuerMaster, CollectionTypeMaster, ParseLinks,
				$stateParams, $http) {
			$scope.instrEnabled = true;
			$scope.expenseDetails = {};
			$scope.collectiontypemasters = [];
			$scope.expenseDetails.expenseDt = new Date();
			$scope.expenseDetailsId = $stateParams.id;
			$scope.load = function(id) {
				ExpenseDetails.get({
					id : id
				}, function(result) {
					$scope.expenseDetails = result;
				});
			};

			$scope.getLocation = function(val) {
				//$scope.isValidCust = false;
				//console.log(val);

				return $http.get('api/collectionTypeMasters/searchEXP/' + val, {
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
				var arr = $item.split("-");
				$scope.custInfo = $item;
				$scope.isValidCust = true;
			};

			$scope.validate = function() {
				if (!$scope.editForm.expenseAmt.$dirty)
					return true;
				if (!$scope.editForm.paymentTypes.$dirty)
					return true;
				if (!$scope.editForm.collectionTypeMaster.$dirty)
					return true;
				if ($scope.editForm.expenseAmt.$invalid)
					return true;
				if ($scope.editForm.paymentTypes.$invalid)
					return true;
				if ($scope.editForm.collectionTypeMaster.$invalid)
					return true;
				if ($scope.instrEnabled) {
					if (!$scope.editForm.instrNo.$dirty)
						return true;
					if (!$scope.editForm.instrDt.$dirty)
						return true;
					if (!$scope.editForm.instrumentIssuerMaster.$dirty)
						return true;
					if ($scope.editForm.instrNo.$invalid)
						return true;
					if ($scope.editForm.instrDt.$invalid)
						return true;
					if ($scope.editForm.instrumentIssuerMaster.$invalid)
						return true;
				}
				return false;
			}

			// This code is used to get Expense Types from
			// collection_type_master table based on txn_type E.
			CollectionTypeMaster.query({
				page : $scope.page,
				size : 20,
				txnType : 'E'
			}, function(result, headers) {
				$scope.links = ParseLinks.parse(headers('link'));
				for (var i = 0; i < result.length; i++) {
					$scope.collectiontypemasters.push(result[i]);
				}
			});

			if ($scope.expenseDetailsId != null)
				$scope.load($scope.expenseDetailsId);

			var onSaveSuccess = function(result) {
				$scope.$emit('watererpApp:expenseDetailsUpdate', result);
				$scope.isSaving = false;
				$state.go('expenseDetails');
			};

			var onSaveError = function(result) {
				$scope.isSaving = false;
			};

			$scope.save = function() {
				$scope.isSaving = true;
				if ($scope.expenseDetails.id != null) {
					ExpenseDetails.update($scope.expenseDetails, onSaveSuccess,
							onSaveError);
				} else {
					ExpenseDetails.save($scope.expenseDetails, onSaveSuccess,
							onSaveError);
				}
			};

			$scope.paymenttypess = PaymentTypes.query();
			$scope.instrumentissuermasters = InstrumentIssuerMaster.query();
			// $scope.collectiontypemasters = CollectionTypeMaster.query();

			$scope.datePickerForExpenseDt = {};

			$scope.datePickerForExpenseDt.status = {
				opened : false
			};

			$scope.datePickerForExpenseDtOpen = function($event) {
				$scope.datePickerForExpenseDt.status.opened = true;
			};
			$scope.datePickerForInstrDt = {};

			$scope.datePickerForInstrDt.status = {
				opened : false
			};

			$scope.datePickerForInstrDtOpen = function($event) {
				$scope.datePickerForInstrDt.status.opened = true;
			};

			$scope.resetInstr = function(paymentMode) {
				if (paymentMode === 'CASH') {
					$scope.instrEnabled = false;
					$scope.expenseDetails.instrNo = null;
					$scope.expenseDetails.instrDt = null;
					$scope.expenseDetails.instrumentIssuerMaster = null;
				} else
					$scope.instrEnabled = true;
			}
		});