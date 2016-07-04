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
					$scope.customer.ChangeCaseType = "CONNECTIONCATEGORY";

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

					$scope.confirm = function() {
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

					$scope.validateCategory = function(oldCategory,
							newCategory) {
						if (oldCategory === newCategory) {
							alert("Selected Category Same as Previous");
							$scope.customer.newTariffCategory = {};
						} else if (newCategory === 1) {
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
					/*$scope.getCustDetails = function(can) {
						CustDetailsSearchCAN
								.get(
										{
											can : can
										},
										function(result) {
											$scope.custDetails = result;
											$scope.customer.prevMeterReading = $scope.custDetails.prevReading;
											$scope.customer.oldTariffCategory = $scope.custDetails.tariffCategoryMaster;
											$scope.customer.previousEmail = $scope.custDetails.email;
										});
					};*/

					// getApplicationTxn by CAN
					/*$scope.getApplicationTxn = function(can) {
						ApplicationTxnSearchCAN.get({
							can : can
						}, function(result) {
							$scope.applicationTxn = result;
						});
					};*/

					//to get active can
					$scope.getActiveCAN = function(can) {
						$scope.customer.can = can;
						$scope.customer.changeType = "CONNECTIONCATEGORY";
						return $http.post('/api/customers/getActiveCan',
								$scope.customer).then(
								function(response) {
									$scope.custDetails = response.data.custDetails;
									$scope.message = null;
									$scope.customer.prevMeterReading = $scope.custDetails.prevReading;
									//if(response.data.applicationTxn != null){
										$scope.customer.oldTariffCategory = response.data.custDetails.tariffCategoryMaster;
										$scope.customer.prevOrganizationName = response.data.custDetails.organisationName;
										$scope.customer.prevDesignation = response.data.custDetails.designation;
									//}
									if(response.data.customer != null){
										if(response.data.customer.status == 3){
											$scope.customer.oldTariffCategory = response.data.customer.presentCategory;
											$scope.customer.prevOrganizationName = response.data.customer.organizationName;
											$scope.customer.prevDesignation = response.data.customer.designation;
											//$scope.customer.prevMeterReading = response.data.customer.meterReading;
										}
										else{
											$scope.isSaving = true;
											$scope.message = "Category change request already submitted for the the CAN: "+can;
										}
									}
									//console.log("Server response:"+ JSON.stringify(response.data));
								});
					}

					// when selected searched CAN in DropDown
					$scope.onSelect = function($item, $model, $label) {
						console.log($item);
						var arr = $item.split("-");
						$scope.custDetails = {};
						$scope.customer = {};
						$scope.custDetails.can = arr[0].trim();
						$scope.custDetails.name = arr[1];
						$scope.custDetails.address = arr[2];
						//$scope.getCustDetails($scope.custDetails.can);
						//$scope.getApplicationTxn($scope.custDetails.can);
						$scope.getActiveCAN($scope.custDetails.can);
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
						$scope.customer.ChangeCaseType = "CONNECTIONCATEGORY";
						$scope.isSaving = true;
						if ($scope.customer.id != null) {
							Customer.update($scope.customer, onSaveSuccess,
									onSaveError);
						} else {
							Customer.save($scope.customer, onSaveSuccess,
									onSaveError);
						}
					};

					/*$scope.saveChanges = function() {
						console.log("WorkflowDTO data being posted to server:"
								+ JSON.stringify($scope.workflowDTO));
						return $http.post('/api/workflowTxnDetailsArr',
								$scope.workflowDTO).then(
								function(response) {
									console.log("Server response:"
											+ JSON.stringify(response));
								});
					}*/


					$scope.checkReading = function(prvReading, newReading) {
						if (prvReading >= newReading) {
							$scope.editForm.newMeterReading.$setValidity(
									"ltPrevious", true);
							return true;
						} else {
							$scope.editForm.newMeterReading.$setValidity(
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
