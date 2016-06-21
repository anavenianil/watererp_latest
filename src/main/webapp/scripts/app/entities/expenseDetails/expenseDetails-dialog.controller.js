'use strict';

angular.module('watererpApp').controller(
		'ExpenseDetailsDialogController',
		function($scope, $state, ExpenseDetails, PaymentTypes,
				BankMaster, CollectionTypeMaster, ParseLinks,
				$stateParams, $http) {
			$scope.isValidExpense = false;
			$scope.instrEnabled = false;
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
				$scope.isValidExpense = false;
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
				$scope.expenseDetails.collectionTypeMaster = $item;
				$scope.isValidExpense = true;
			};

			$scope.validate = function() {
				if (!$scope.isValidExpense)
					return true;
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

			$scope.validateInstrDt = function() {
				var today = moment();
				var instrDt = moment($scope.collDetails.instrDt);

				var duration = moment.duration(today.diff(instrDt));
				var months = duration.asMonths();

				console.log("Months:" + months)
				var days = duration.asDays();
				if (days < -1){
					$scope.editForm.instrDt.$setValidity("future",
							false);
					$scope.editForm.instrDt.$setValidity("veryOld",
							true);
				}
				else if (months > 6) {
					$scope.editForm.instrDt.$setValidity("future",
							true);
					$scope.editForm.instrDt.$setValidity("veryOld",
							false);
				} else {
					$scope.editForm.instrDt
							.$setValidity("future", true);
					$scope.editForm.instrDt.$setValidity("veryOld",
							true);
				}

			}

			
			
			$scope.validateDate = function() {
				var today = moment();
				var receiptDt = moment($scope.collDetails.receiptDt);

				var duration = moment.duration(today.diff(receiptDt));
				var months = duration.asMonths();

				console.log("This is today:" + today);
				console.log("This is receipt dt:" + receiptDt);
				console.log("Duration:" + months);

				if (months > 6) {
					console.log("Very old, today:" + today
							+ ", receipt dt:" + receiptDt);
					$scope.editForm.receiptDt.$setValidity("veryOld",
							false);
					$scope.editForm.receiptDt.$setValidity("future",
							true);
				} else if (months < -1) {
					console.log("Future, today:" + today
							+ ", receipt dt:" + receiptDt);
					$scope.editForm.receiptDt.$setValidity("future",
							false);
					$scope.editForm.receiptDt.$setValidity("veryOld",
							true);
				} else {
					console.log("Ok, today:" + today + ", receipt dt:"
							+ receiptDt);
					$scope.editForm.receiptDt.$setValidity("future",
							true);
					$scope.editForm.receiptDt.$setValidity("veryOld",
							true);
				}
			}
			
			
			
			
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
			$scope.bankMasters = BankMaster.query();
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
				if (paymentMode.toUpperCase()  === 'CASH') {
					$scope.instrEnabled = false;
					$scope.expenseDetails.instrNo = null;
					$scope.expenseDetails.instrDt = null;
					$scope.expenseDetails.instrumentIssuerMaster = null;
				} else
					$scope.instrEnabled = true;
			}
		});