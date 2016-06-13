'use strict';

angular
		.module('watererpApp')
		.controller(
				'RevDetailsDialogController',
				function($scope, $state, CollDetails, RevDetails, ParseLinks,
						$stateParams, PaymentTypes, InstrumentIssuerMaster,
						CustDetails, CustDetailsService, CollectionTypeMaster,
						RevenueTypeMaster, $http) {

					$scope.isValidCust = false;
					$scope.instrEnabled = false;
					$scope.paymenttypess = PaymentTypes.query();
					$scope.instrumentissuermasters = InstrumentIssuerMaster
							.query();
					// $scope.collectionTypeMasters =
					// CollectionTypeMaster.query();
					// $scope.revenueTypeMasters = RevenueTypeMaster.query();
					$scope.revenueTypeMasters = [];

					$scope.custDetails = {};
					$scope.collDetails = {};
					$scope.collDetails.custDetails = {};
					$scope.collectionTypeMasters = [];
					$scope.collDetails.collectionTypeMaster = {};

					$scope.collDetails.receiptDt = new Date();

					$scope.collDetails.txnStatus = "R";
					$scope.collDetails.collectionTypeMaster.id = 1;

					var onSaveSuccess = function(result) {
						$scope.$emit('watererpApp:collDetailsUpdate', result);
						$scope.isSaving = false;
						$state.go('revDetailss');
					};

					var onSaveError = function(result) {
						$scope.isSaving = false;
					};

					$scope.validate = function() {

						if ($scope.isSaving)
							return true;
						if ($scope.editForm.receiptDt.$invalid)
							return true;
						if ($scope.editForm.receiptAmt.$invalid)
							return true;
						if ($scope.editForm.paymentTypes.$invalid)
							return true;

						if ($scope.instrEnabled) {
							if (!$scope.editForm.field_instrNo.$dirty)
								return true;
							if (!$scope.editForm.field_instrDt.$dirty)
								return true;
							if (!$scope.editForm.instrumentIssuerMaster.$dirty)
								return true;
							if ($scope.editForm.field_instrNo.$invalid)
								return true;
							if ($scope.editForm.field_instrDt.$invalid)
								return true;
							if ($scope.editForm.instrumentIssuerMaster.$invalid)
								return true;
						}
						return false;
					}

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
						if ($scope.validate())
							return;

						$scope.isSaving = true;
						RevDetails.save($scope.collDetails, onSaveSuccess,
								onSaveError);
					};

					CollectionTypeMaster
							.query(
									{
										page : $scope.page,
										size : 20,
										txnType : 'R'
									},
									function(result, headers) {
										$scope.links = ParseLinks
												.parse(headers('link'));
										for (var i = 0; i < result.length; i++) {
											$scope.revenueTypeMasters
													.push(result[i]);
										}
										$scope.collDetails.collectionTypeMaster.id = $scope.revenueTypeMasters[0].id;
									});

					$scope.datePickerForReceiptDt = {};

					$scope.datePickerForReceiptDt.status = {
						opened : false
					};

					$scope.datePickerForReceiptDtOpen = function($event) {
						$scope.datePickerForReceiptDt.status.opened = true;
					};
					$scope.datePickerForInstrDt = {};

					$scope.datePickerForInstrDt.status = {
						opened : false
					};

					$scope.datePickerForInstrDtOpen = function($event) {
						$scope.datePickerForInstrDt.status.opened = true;
					};
					$scope.datePickerForCollTime = {};

					$scope.datePickerForCollTime.status = {
						opened : false
					};

					$scope.datePickerForCollTimeOpen = function($event) {
						$scope.datePickerForCollTime.status.opened = true;
					};

					$scope.resetInstr = function(paymentMode) {
						if (paymentMode.toUpperCase() === 'CASH') {
							$scope.instrEnabled = false;
							$scope.collDetails.instrNo = null;
							$scope.collDetails.instrDt = null;
							$scope.collDetails.instrumentIssuerMaster = null;
						} else
							$scope.instrEnabled = true;
					}
				});
