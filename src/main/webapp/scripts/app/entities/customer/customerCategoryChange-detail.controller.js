'use strict';

angular
		.module('watererpApp')
		.controller(
				'CustomerCategoryChangeDetailController',
				function($scope, $stateParams, CustDetails,
						$state, $http, ParseLinks, RequestWorkflowHistory, Customer, CustDetailsSearchCAN, Principal, $window) {

					$scope.customer = {};
					$scope.customer.changeType = "CONNECTIONCATEGORY";
					
					$scope.workflowDTO = {};
					$scope.workflowDTO.customer = {};
					
					$scope.custDetails = {};
					$scope.orgRole = Principal.getOrgRole();
					
					$scope.datePickerForApprovedDate = {};

			        $scope.datePickerForApprovedDate.status = {
			            opened: false
			        };

			        $scope.datePickerForApprovedDateOpen = function($event) {
			            $scope.datePickerForApprovedDate.status.opened = true;
			        };
					
					//$scope.tariffcategorymasters = TariffCategoryMaster.query();
					//$scope.pipeSizeMasters = PipeSizeMaster.query();
					//$scope.workflowDTO = {};
					//$scope.workflowDTO.requestWorkflowHistory = {};
					//$scope.workflowDTO.workflowTxnDetailss = {};
					//$scope.applicationTxn = {};
					//$scope.workflowDTO.workflowTxnDetailss = [];
					
					
					// get cust details by CAN
					$scope.getCustDetails = function(can) {
						CustDetailsSearchCAN.get({can : can},function(result) {
											$scope.custDetails = result;
											$scope.customer.meterReading = $scope.custDetails.prevReading;
											$scope.customer.tariffCategoryMaster = $scope.custDetails.tariffCategoryMaster;
										});
					};
					
					$scope.load = function (id) {
			            Customer.get({id: id}, function(result) {
			                $scope.customer = result;
			                $scope.getCustDetails($scope.customer.can);
			                $scope.customer.remarks = "";
			                $scope.workflowDTO.customer = $scope.customer; 
			            });
			        };
					
			        if($stateParams.id != null){
			        	$scope.load($stateParams.id);
			        }
					
					
					console.log("These are the state:"
							+ JSON.stringify($state.current.name));

					$scope.getWorkflowHistoryByDomainId = function() {
			        	$scope.requestWorkflowHistorys = [];
			            RequestWorkflowHistory.query({page: $scope.page, size: 20, dimainObjectId: $stateParams.id, requestTypeId: $stateParams.requestTypeId}, function(result, headers) {
			                $scope.links = ParseLinks.parse(headers('link'));
			                for (var i = 0; i < result.length; i++) {
			                    $scope.requestWorkflowHistorys.push(result[i]);
			                }
			            });
			        };
			        $scope.getWorkflowHistoryByDomainId();

					

					$scope.dtmax = new Date();

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

				
					
					$scope.save = function () {
			            $scope.isSaving = true;
			            if ($scope.customer.id != null) {
			                Customer.update($scope.customer);
			            } else {
			                Customer.save($scope.customer);
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
				
					//approve a request
					$scope.approve = function(customer){
			        	return $http.post('/api/customers/customersApprove',
								customer).then(
								function(response) {
									$state.go('customer.categoryChangeList');
								});
			        }
					
					//declineRequest
					$scope.declineRequest = function(workflowDTO){
			        	return $http.post('/api/customers/declineRequest',
								workflowDTO).then(
								function(response) {
									$window.history.back();
								});
			        }
					
					$scope.canDecline = function() {
						var ret = false;
						switch ($scope.customer.status) {
						case 0:
							if ($scope.orgRole.orgRoleName === 'Technical Manager')
								ret = true;
							break;
						case 1:
							if ($scope.orgRole.orgRoleName === 'Billing Officer')
								ret = true;
							break;
						default:
							break;
						}
						return ret;
					}
				});
