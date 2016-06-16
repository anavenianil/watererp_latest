'use strict';

angular
		.module('watererpApp')
		.controller(
				'CustomerCategoryChangeController',
				function($scope, $stateParams, CustDetails,
						TariffCategoryMaster, $state, $http,
						CustDetailsSearchCAN, PipeSizeMaster,
						ApplicationTxnSearchCAN, ParseLinks,
						RequestWorkflowHistory, Customer) {

					$scope.customer = {};
					$scope.customer.changeType = "CONNECTIONCATEGORY";

					$scope.custDetails = {};

					$scope.tariffcategorymasters = TariffCategoryMaster.query();
					$scope.pipeSizeMasters = PipeSizeMaster.query();
					$scope.workflowDTO = {};
					$scope.workflowDTO.requestWorkflowHistory = {};
					$scope.workflowDTO.workflowTxnDetailss = {};
					$scope.applicationTxn = {};
					$scope.workflowDTO.workflowTxnDetailss = [];
					$scope.referenceNo = "";

					console.log("These are the state:"
							+ JSON.stringify($state.current.name));

					$scope.dtmax = new Date();

					if ($stateParams.id != null) {
						$scope.load($stateParams.id);
					}

					$scope.clear = function() {
						// $uibModalInstance.dismiss('cancel');
						$('#saveSuccessfullyModal').modal('hide');
						$state.go('customer.categoryChangeList');

					};

					$scope.datePickerForRequestedDate = {};

					$scope.datePickerForRequestedDate.status = {
						opened : false
					};

					$scope.datePickerForRequestedDateOpen = function($event) {
						$scope.datePickerForRequestedDate.status.opened = true;
					};

					$scope.validateCategory = function(prevCategory,
							presentCategory) {
						if (prevCategory === presentCategory) {
							alert("Selected Category Same as Previous");
							$scope.customer.presentCategory = {};
						} else if (presentCategory === 1) {

							$scope.instrEnabled = false;
							$scope.customer.organizationName = null;
						} else
							$scope.instrEnabled = true;

					}

					// to search CAN
					$scope.getLocation = function(val) {
						$scope.isValidCust = false;
						return $http.get('api/custDetailss/searchCAN/' + val, {
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

					// get cust details by CAN
					$scope.getCustDetails = function(can) {
						CustDetailsSearchCAN
								.get(
										{
											can : can
										},
										function(result) {
											$scope.custDetails = result;
											$scope.customer.meterReading = $scope.custDetails.prevReading;
											$scope.customer.tariffCategoryMaster = $scope.custDetails.tariffCategoryMaster;
										});
					};

					// getApplicationTxn by CAN
					$scope.getApplicationTxn = function(can) {
						ApplicationTxnSearchCAN.get({
							can : can
						}, function(result) {
							$scope.applicationTxn = result;
						});
					};

					//to get active can
					$scope.getActiveCAN = function(can) {
						$scope.customer.can = can;
						return $http.post('/api/customers/getActiveCan',
								$scope.customer).then(
								function(response) {

									$scope.custDet = [];
									for (var i = 0; i < response.data.length; i++) {
										$scope.txnList.push(response.data[i]);
									}
								
									console.log("Server response:"
											+ JSON.stringify(response));
								});
					}

					// when selected searched CAN in DropDown
					$scope.onSelect = function($item, $model, $label) {
						console.log($item);
						var arr = $item.split("-");
						$scope.custDetails = {};
						$scope.custDetails.can = arr[0].trim();
						$scope.custDetails.name = arr[1];
						$scope.custDetails.address = arr[2];
						$scope.getCustDetails($scope.custDetails.can);
						$scope.getApplicationTxn($scope.custDetails.can);
						//$scope.getActiveCAN($scope.custDetails.can);
						$scope.custInfo = "";
						$scope.isValidCust = true;
						$scope.referenceNo = $scope.custDetails.can;
						$scope.customer.can = $scope.custDetails.can
					};

					var onSaveSuccess = function(result) {
						$scope.isSaving = false;
						$scope.customer.id = result.id;
						$('#saveSuccessfullyModal').modal('show');

						//$state.go('customer.categoryChangeList');
					};

					var onSaveError = function(result) {
						$scope.isSaving = false;
					};
					$scope.save = function() {
						$scope.isSaving = true;
						if ($scope.customer.id != null) {
							Customer.update($scope.customer, onSaveSuccess,
									onSaveError);
						} else {
							Customer.save($scope.customer, onSaveSuccess,
									onSaveError);
						}
					};

					$scope.saveChanges = function() {
						console.log("WorkflowDTO data being posted to server:"
								+ JSON.stringify($scope.workflowDTO));

						return $http.post('/api/workflowTxnDetailsArr',
								$scope.workflowDTO).then(
								function(response) {
									console.log("Server response:"
											+ JSON.stringify(response));
								});
					}


					$scope.checkReading = function(prvReading, newReading) {
						if (prvReading >= newReading) {

							$scope.editForm.presentReading.$setValidity(
									"ltPrevious", true);
							return true;
						} else {

							$scope.editForm.presentReading.$setValidity(
									"ltPrevious", false);
							return false;
						}
					}
					//approve a request
					$scope.approve = function(workflowDTO) {
						console.log(workflowDTO);
						return $http.post('/api/workflowTxnDetailsApprove',
								$scope.workflowDTO).then(
								function(response) {
									console.log("Server response:"
											+ JSON.stringify(response));
								});
					}

				});
