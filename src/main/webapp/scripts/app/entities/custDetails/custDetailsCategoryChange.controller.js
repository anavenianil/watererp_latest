'use strict';

angular
		.module('watererpApp')
		.controller(
				'CustDetailsCategoryChangeController',
				/*
				 * ['$scope', '$stateParams', '$uibModalInstance', 'entity',
				 * 'CustDetails', 'TariffCategoryMaster',
				 */
				function($scope, $stateParams, /* $uibModalInstance, entity, */
				CustDetails, TariffCategoryMaster, $state, $http,
						CustDetailsSearchCAN, WorkflowTxnDetails,
						PipeSizeMaster, ApplicationTxnSearchCAN, ParseLinks) {

					$scope.custDetails = {};
					$scope.tariffcategorymasters = TariffCategoryMaster.query();
					$scope.pipeSizeMasters = PipeSizeMaster.query();
					$scope.workflowDTO = {};
					$scope.workflowDTO.requestWorkflowHistory = {};
					$scope.workflowDTO.workflowTxnDetailss = {};
					$scope.applicationTxn = {};

					console.log("These are the state:"
							+ JSON.stringify($state.current.name));

					$scope.workflowDTO.workflowTxnDetailss = [];

					if ($state.current.name === "custDetails.categoryChange") {
						$scope.workflowDTO.workflowTxnDetailss[0] = {};
						$scope.workflowDTO.workflowTxnDetailss[1] = {};

						$scope.workflowDTO.workflowTxnDetailss[2] = {};
						$scope.workflowDTO.workflowTxnDetailss[3] = {};
						$scope.workflowDTO.workflowTxnDetailss[4] = {};
						$scope.workflowDTO.workflowTxnDetailss[5] = {};
						$scope.workflowDTO.workflowTxnDetailss[6] = {};

						$scope.workflowDTO.workflowTxnDetailss[0].columnName = "prevReading";
						$scope.workflowDTO.workflowTxnDetailss[0].requestMaster = {};
						$scope.workflowDTO.workflowTxnDetailss[0].requestMaster.id = 8;

						$scope.workflowDTO.workflowTxnDetailss[1].columnName = "TarrifCategoryMaster";
						$scope.workflowDTO.workflowTxnDetailss[1].requestMaster = {};
						$scope.workflowDTO.workflowTxnDetailss[1].requestMaster.id = 8;

						$scope.workflowDTO.workflowTxnDetailss[2].columnName = "organisation";
						$scope.workflowDTO.workflowTxnDetailss[2].requestMaster = {};
						$scope.workflowDTO.workflowTxnDetailss[2].requestMaster.id = 8;

						$scope.workflowDTO.workflowTxnDetailss[3].columnName = "organisationName";
						$scope.workflowDTO.workflowTxnDetailss[3].requestMaster = {};
						$scope.workflowDTO.workflowTxnDetailss[3].requestMaster.id = 8;

						$scope.workflowDTO.workflowTxnDetailss[4].columnName = "designation";
						$scope.workflowDTO.workflowTxnDetailss[4].requestMaster = {};
						$scope.workflowDTO.workflowTxnDetailss[4].requestMaster.id = 8;

						$scope.workflowDTO.workflowTxnDetailss[5].columnName = "deedDoc";
						$scope.workflowDTO.workflowTxnDetailss[5].requestMaster = {};
						$scope.workflowDTO.workflowTxnDetailss[5].requestMaster.id = 8;

						$scope.workflowDTO.workflowTxnDetailss[6].columnName = "agreementDoc";
						$scope.workflowDTO.workflowTxnDetailss[6].requestMaster = {};
						$scope.workflowDTO.workflowTxnDetailss[6].requestMaster.id = 8;
					}

					$scope.dtmax = new Date();

					if ($stateParams.id != null) {
						$scope.load($stateParams.id);
					}

					$scope.clear = function() {
						// $uibModalInstance.dismiss('cancel');
					};

					$scope.datePickerForRequestedDate = {};

					$scope.datePickerForRequestedDate.status = {
						opened : false
					};

					$scope.datePickerForRequestedDateOpen = function($event) {
						$scope.datePickerForRequestedDate.status.opened = true;
					};

					$scope.disableOrg = function(categoryId) {
						console.log("Category id: " + categoryId);
						if (categoryId === 1) {
							$scope.workflowDTO.workflowTxnDetailss[2] = false;
							$scope.workflowDTO.workflowTxnDetailss[3] = "";
							$scope.workflowDTO.workflowTxnDetailss[4] = "";
							$scope.workflowDTO.workflowTxnDetailss[5] = "";
							$scope.workflowDTO.workflowTxnDetailss[6] = "";
						}
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
											$scope.workflowDTO.workflowTxnDetailss[0].previousValue = $scope.custDetails.prevReading;
											// $scope.workflowDTO.previousValue
											// =
											// $scope.custDetails.tariffCategoryMaster;
											//$scope.workflowDTO.workflowTxnDetailss[1].previousValue = $scope.custDetails.tariffCategoryMaster.id;
											$scope.workflowDTO.workflowTxnDetailss[1].previousValue = parseInt($scope.custDetails.tariffCategoryMaster.id,10)
										});
					};

					// getApplicationTxn by CAN
					$scope.getApplicationTxn = function(can) {
						ApplicationTxnSearchCAN
								.get(
										{
											can : can
										},
										function(result) {
											$scope.applicationTxn = result;
											$scope.workflowDTO.workflowTxnDetailss[2].previousValue = $scope.applicationTxn.organization;
											$scope.workflowDTO.workflowTxnDetailss[3].previousValue = $scope.applicationTxn.organizationName;
											$scope.workflowDTO.workflowTxnDetailss[4].previousValue = $scope.applicationTxn.designation
											$scope.workflowDTO.workflowTxnDetailss[5].previousValue = $scope.applicationTxn.deedDoc;
											$scope.workflowDTO.workflowTxnDetailss[6].previousValue = $scope.applicationTxn.agreementDoc;
										});
					};

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
						$scope.custInfo = "";
						$scope.isValidCust = true;
					};

					$scope.saveChanges = function() {

						$scope.workflowDTO.workflowTxnDetailss[1].previousValue = $scope.workflowDTO.workflowTxnDetailss[1].previousValue.id;
						console.log("WorkflowDTO data being posted to server:"
								+ JSON.stringify($scope.workflowDTO));

						return $http.post('/api/workflowTxnDetailsArr',
								$scope.workflowDTO).then(
								function(response) {
									console.log("Server response:"
											+ JSON.stringify(response));
									// var res =
									// response.data.map(function(item) {
									// return item;
									// });
									// return res;
								});
					}

					$scope.getWorkflowTxnDetails = function(requestId) {
						WorkflowTxnDetails
								.query(
										{
											page : $scope.page,
											size : 20,
											requestId : requestId
										},
										function(result, headers) {
											$scope.links = ParseLinks
													.parse(headers('link'));
											for (var i = 0; i < result.length; i++) {
												if (i == 1) {
													result[i].previousValue = parseInt(
															result[i].previousValue,
															10);
													result[i].newValue = parseInt(
															result[i].newValue,
															10);
												}
												$scope.workflowDTO.workflowTxnDetailss
														.push(result[i]);
											}
										});
					};

					if ($stateParams.requestId != null) {
						$scope.getWorkflowTxnDetails($stateParams.requestId);
					}
				}/*]*/);
