'use strict';

angular
		.module('watererpApp')
		.controller(
				'CustomerCategoryChangeDetailController',
				function($scope, $stateParams, CustDetails,
						$state, $http, ParseLinks, RequestWorkflowHistory, Customer, CustDetailsSearchCAN, Principal, $window, ApplicationTxnSearchCAN) {

					$scope.customer = {};
					$scope.customer.changeType = "CONNECTIONCATEGORY";
					
					$scope.workflowDTO = {};
					//$scope.workflowDTO.customer = {};
					
					$scope.custDetails = {};
					$scope.maxDt = new Date();
					//$scope.orgRole = Principal.getOrgRole();
					$scope.orgRole = {};
					Principal.getOrgRole().then(function(response) {
						$scope.orgRole = response;
					});
					
					$scope.datePickerForApprovedDate = {};

			        $scope.datePickerForApprovedDate.status = {
			            opened: false
			        };

			        $scope.datePickerForApprovedDateOpen = function($event) {
			            $scope.datePickerForApprovedDate.status.opened = true;
			        };
			        
			        
			        $scope.datePickerForChangedDate = {};

			        $scope.datePickerForChangedDate.status = {
			            opened: false
			        };

			        $scope.datePickerForChangedDateOpen = function($event) {
			            $scope.datePickerForChangedDate.status.opened = true;
			        };
					
			        // getApplicationTxn by CAN
					$scope.getApplicationTxn = function(can) {
						ApplicationTxnSearchCAN.get({can : can},
										function(result) {
											$scope.applicationTxn = result;
										});
					};
					
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
			            	$scope.workflowDTO.customer = result;
			                $scope.getCustDetails(result.can);
			                $scope.getApplicationTxn(result.can);
			                $scope.customer.remarks = "";
			                //$scope.workflowDTO.customer = $scope.customer; 
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
						
						$('#saveSuccessfullyModal').modal('hide');
						$state.go('customer.categoryChangeList');
					};

					$scope.datePickerForAssignedDate = {};

					$scope.datePickerForAssignedDate.status = {
						opened : false
					};

					$scope.datePickerForAssignedDateOpen = function($event) {
						$scope.datePickerForAssignedDate.status.opened = true;
					};

				
					
					/*$scope.save = function () {
			            $scope.isSaving = true;
			            if ($scope.customer.id != null) {
			                Customer.update($scope.customer);
			            } else {
			                Customer.save($scope.customer);
			            }
			        };*/

			        
					//approve a request
					$scope.approve = function(workflowDTO){
						$scope.isSaving = true;
			        	return $http.post('/api/customers/customersApprove',
								workflowDTO).then(
								function(response) {
									console.log("Server response:"
											+ JSON.stringify(response));
									
									//$state.go('customer.categoryChangeList');
									$('#saveSuccessfullyModal').modal('show');
								});
			        }
					
					//declineRequest
					$scope.declineRequest = function(workflowDTO){
			        	return $http.post('/api/customers/declineRequest',
								workflowDTO).then(
								function(response) {
									console.log("Server response:"
											+ JSON.stringify(response));
									$window.history.back();
								});
			        }
					
					$scope.canDecline = function() {
						var ret = false;
						switch ($scope.customer.status) {
						case 'INITIATED':
							if ($scope.orgRole.id === 10)
								ret = true;
							break;
						case 'PROCESSING':
							if ($scope.orgRole.id === 16)
								ret = true;
							break;
						default:
							break;
						}
						return ret;
					}
				});
