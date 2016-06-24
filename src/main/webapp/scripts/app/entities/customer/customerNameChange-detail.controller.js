'use strict';

angular
		.module('watererpApp')
		.controller(
				'CustomerNameChangeDetailController',
				function($scope, $stateParams, CustDetails,$window,
						$state, $http, ParseLinks, RequestWorkflowHistory, Customer, CustDetailsSearchCAN, Principal) {

					$scope.workflowDTO = {};
					$scope.workflowDTO.customer = {};
					$scope.workflowDTO.customer.changeType = "CHANGENAME";
					
					$scope.custDetails = {};
					//$scope.orgRole = Principal.getOrgRole();
					Principal.getOrgRole().then(function(response) {
						$scope.orgRole = response;
					});
					
					// get cust details by CAN
					$scope.getCustDetails = function(can) {
						CustDetailsSearchCAN.get({can : can},function(result) {
											$scope.custDetails = result;
										});
					};
					
					$scope.load = function (id) {
			            Customer.get({id: id}, function(result) {
			                $scope.workflowDTO.customer = result;
			                $scope.customer = result;
			                $scope.getCustDetails(result.can);
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

					

					$scope.maxDt = new Date();

					$scope.confirm = function() {
						// $uibModalInstance.dismiss('cancel');
						 $("#saveSuccessfullyModal").modal("hide");
			             $state.go('customer.nameChangeList');
					};

					//approve a request
					$scope.approve = function(workflowDTO){
			        	//console.log(customer);
			        	return $http.post('/api/customers/customersApprove',
								workflowDTO).then(
								function(response) {
									console.log("Server response:"
											+ JSON.stringify(response));
							           $("#saveSuccessfullyModal").modal("show");
									//$state.go('customer.nameChangeList');
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
						switch ($scope.workflowDTO.customer.status) {
						case 1:
							if ($scope.orgRole.id === 10)
								ret = true;
							break;
						case 2:
							if ($scope.orgRole.id === 22)
								ret = true;
							break;
						case 3:
							if ($scope.orgRole.id === 16)
								ret = true;
							break;
						default:
							break;
						}
						return ret;
					}
					
					$scope.datePickerForApprovedDate = {};

					$scope.datePickerForApprovedDate.status = {
						opened : false
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
				});
