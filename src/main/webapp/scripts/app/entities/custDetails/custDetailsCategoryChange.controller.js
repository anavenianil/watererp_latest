'use strict';

angular
		.module('watererpApp')
		.controller(
				'CustDetailsCategoryChangeController',
				function($scope, $stateParams, CustDetails,
						TariffCategoryMaster, $state, $http,
						CustDetailsSearchCAN, WorkflowTxnDetails,
						PipeSizeMaster, ApplicationTxnSearchCAN, ParseLinks, RequestWorkflowHistory) {

					$scope.custDetails = {};
					$scope.tariffcategorymasters = TariffCategoryMaster.query();
					$scope.pipeSizeMasters = PipeSizeMaster.query();
					$scope.changeCaseDTO = {};
					$scope.changeCaseDTO.requestWorkflowHistory = {};
					$scope.changeCaseDTO.workflowTxnDetailss = {};
					$scope.applicationTxn = {};
					$scope.changeCaseDTO.workflowTxnDetailss = [];
					$scope.referenceNo = "";
					
					console.log("These are the state:"
							+ JSON.stringify($state.current.name));

					$scope.getWorkflowHistoryByDomainId = function(domainId) {
			        	$scope.requestWorkflowHistorys = [];
			            RequestWorkflowHistory.query({page: $scope.page, size: 20, dimainObjectId: domainId, requestTypeId: $stateParams.requestTypeId}, function(result, headers) {
			                $scope.links = ParseLinks.parse(headers('link'));
			                for (var i = 0; i < result.length; i++) {
			                    $scope.requestWorkflowHistorys.push(result[i]);
			                }
			                $scope.changeCaseDTO.requestWorkflowHistory.assignedDate = $scope.requestWorkflowHistorys[0].assignedDate;
			            });
			        };
					

					if ($state.current.name === "custDetails.categoryChange") {
						$scope.changeCaseDTO.workflowTxnDetailss[0] = {};
						$scope.changeCaseDTO.workflowTxnDetailss[1] = {};

						$scope.changeCaseDTO.workflowTxnDetailss[2] = {};
						$scope.changeCaseDTO.workflowTxnDetailss[3] = {};
						$scope.changeCaseDTO.workflowTxnDetailss[4] = {};
						$scope.changeCaseDTO.workflowTxnDetailss[5] = {};
						$scope.changeCaseDTO.workflowTxnDetailss[6] = {};

						$scope.changeCaseDTO.workflowTxnDetailss[0].columnName = "prevReading";
						$scope.changeCaseDTO.workflowTxnDetailss[0].requestMaster = {};
						$scope.changeCaseDTO.workflowTxnDetailss[0].requestMaster.id = 8;
						$scope.changeCaseDTO.workflowTxnDetailss[0].referenceNumber = $scope.referenceNo;

						$scope.changeCaseDTO.workflowTxnDetailss[1].columnName = "TarrifCategoryMaster";
						$scope.changeCaseDTO.workflowTxnDetailss[1].requestMaster = {};
						$scope.changeCaseDTO.workflowTxnDetailss[1].requestMaster.id = 8;
						$scope.changeCaseDTO.workflowTxnDetailss[1].referenceNumber = $scope.referenceNo;

						$scope.changeCaseDTO.workflowTxnDetailss[2].columnName = "organisation";
						$scope.changeCaseDTO.workflowTxnDetailss[2].requestMaster = {};
						$scope.changeCaseDTO.workflowTxnDetailss[2].requestMaster.id = 8;
						$scope.changeCaseDTO.workflowTxnDetailss[2].referenceNumber = $scope.referenceNo;

						$scope.changeCaseDTO.workflowTxnDetailss[3].columnName = "organisationName";
						$scope.changeCaseDTO.workflowTxnDetailss[3].requestMaster = {};
						$scope.changeCaseDTO.workflowTxnDetailss[3].requestMaster.id = 8;
						$scope.changeCaseDTO.workflowTxnDetailss[3].referenceNumber = $scope.referenceNo;

						$scope.changeCaseDTO.workflowTxnDetailss[4].columnName = "designation";
						$scope.changeCaseDTO.workflowTxnDetailss[4].requestMaster = {};
						$scope.changeCaseDTO.workflowTxnDetailss[4].requestMaster.id = 8;
						$scope.changeCaseDTO.workflowTxnDetailss[4].referenceNumber = $scope.referenceNo;

						$scope.changeCaseDTO.workflowTxnDetailss[5].columnName = "deedDoc";
						$scope.changeCaseDTO.workflowTxnDetailss[5].requestMaster = {};
						$scope.changeCaseDTO.workflowTxnDetailss[5].requestMaster.id = 8;
						$scope.changeCaseDTO.workflowTxnDetailss[5].referenceNumber = $scope.referenceNo;

						$scope.changeCaseDTO.workflowTxnDetailss[6].columnName = "agreementDoc";
						$scope.changeCaseDTO.workflowTxnDetailss[6].requestMaster = {};
						$scope.changeCaseDTO.workflowTxnDetailss[6].requestMaster.id = 8;
						$scope.changeCaseDTO.workflowTxnDetailss[6].referenceNumber = $scope.referenceNo;
					}

					$scope.dtmax = new Date();

					if ($stateParams.id != null) {
						$scope.load($stateParams.id);
					}

					$scope.clear = function() {
						// $uibModalInstance.dismiss('cancel');
					};

					$scope.datePickerForAssignedDate = {};

					$scope.datePickerForAssignedDate.status = {
						opened : false
					};

					$scope.datePickerForAssignedDateOpen = function($event) {
						$scope.datePickerForAssignedDate.status.opened = true;
					};

					$scope.disableOrg = function(categoryId) {
						console.log("Category id: " + categoryId);
						if (categoryId === 1) {
							$scope.changeCaseDTO.workflowTxnDetailss[2] = false;
							$scope.changeCaseDTO.workflowTxnDetailss[3] = "";
							$scope.changeCaseDTO.workflowTxnDetailss[4] = "";
							$scope.changeCaseDTO.workflowTxnDetailss[5] = "";
							$scope.changeCaseDTO.workflowTxnDetailss[6] = "";
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
											$scope.changeCaseDTO.workflowTxnDetailss[0].previousValue = $scope.custDetails.prevReading;
											$scope.changeCaseDTO.workflowTxnDetailss[1].previousValue = parseInt(
													$scope.custDetails.tariffCategoryMaster.id,
													10)
										});
					};

					// getApplicationTxn by CAN
					$scope.getApplicationTxn = function(can) {
						ApplicationTxnSearchCAN.get({can : can},
										function(result) {
											$scope.applicationTxn = result;
											$scope.changeCaseDTO.workflowTxnDetailss[2].previousValue = $scope.applicationTxn.organization;
											$scope.changeCaseDTO.workflowTxnDetailss[3].previousValue = $scope.applicationTxn.organizationName;
											$scope.changeCaseDTO.workflowTxnDetailss[4].previousValue = $scope.applicationTxn.designation
											$scope.changeCaseDTO.workflowTxnDetailss[5].previousValue = $scope.applicationTxn.deedDoc;
											$scope.changeCaseDTO.workflowTxnDetailss[6].previousValue = $scope.applicationTxn.agreementDoc;
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
						$scope.referenceNo = $scope.custDetails.can;
					};

					$scope.saveChanges = function() {
						//$scope.changeCaseDTO.workflowTxnDetailss[1].previousValue = $scope.changeCaseDTO.workflowTxnDetailss[1].previousValue.id;
						for(var i=0; i<$scope.changeCaseDTO.workflowTxnDetailss.length;i++){
							$scope.changeCaseDTO.workflowTxnDetailss[i].referenceNumber = $scope.referenceNo;
						}
						console.log("changeCaseDTO data being posted to server:"
								+ JSON.stringify($scope.changeCaseDTO));

						return $http.post('/api/workflowTxnDetailsArr',
								$scope.changeCaseDTO).then(
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
						$scope.changeCaseDTO.workflowTxnDetailss[0] ={};
						WorkflowTxnDetails.query({
							page : $scope.page,
							size : 20,
							requestId : requestId
						}, function(result, headers) {
							$scope.links = ParseLinks.parse(headers('link'));
							for (var i = 0; i < result.length; i++) {
								if (i == 1) {
									result[i].previousValue = parseInt(
											result[i].previousValue, 10);
									result[i].newValue = parseInt(
											result[i].newValue, 10);
								}
								$scope.changeCaseDTO.workflowTxnDetailss
										.push(result[i]);
							}
							$scope.getCustDetails($scope.changeCaseDTO.workflowTxnDetailss[0].referenceNumber);
							$scope.getWorkflowHistoryByDomainId($scope.changeCaseDTO.workflowTxnDetailss[0].id);
						});
					};

					if ($stateParams.requestId != null) {
						$scope.getWorkflowTxnDetails($stateParams.requestId);
					}
					
					//approve a request
					$scope.approve = function(changeCaseDTO){
			        	console.log(changeCaseDTO);
			        	return $http.post('/api/workflowTxnDetailsApprove',
								$scope.changeCaseDTO).then(
								function(response) {
									console.log("Server response:"
											+ JSON.stringify(response));
								});
						//ApplicationTxnService.approveRequest(id, remarks);
			        }
				});
