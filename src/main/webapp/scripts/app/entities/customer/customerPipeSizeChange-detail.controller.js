'use strict';

angular
		.module('watererpApp')
		.controller(
				'CustomerPipeSizeChangeDetailController',
				function($scope, $stateParams, CustDetails,
						$state, $http, ParseLinks, RequestWorkflowHistory, Customer, CustDetailsSearchCAN, Principal, $window) {

					$scope.customer = {};
					$scope.changeCaseDTO = {};
					$scope.changeCaseDTO.customer = {};
					$scope.changeCaseDTO.customer.changeType = "PIPESIZE";
					$scope.custDetails = {};
					$scope.orgRole = {};
					$scope.user = Principal.getLogonUser();
					Principal.getOrgRole().then(function(response) {
						$scope.orgRole = response;
					});					
					$scope.maxDt = new Date();
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
					
					// get cust details by CAN
					$scope.getCustDetails = function(can) {
						CustDetailsSearchCAN.get({can : can},function(result) {
											$scope.custDetails = result;
										});
					};
					
					$scope.load = function (id) {
			            Customer.get({id: id}, function(result) {
			                $scope.customer = result;
			                $scope.getCustDetails($scope.customer.can);
			                $scope.customer.remarks = "";
			                $scope.changeCaseDTO.customer = result;
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
			                $scope.requestAt = $scope.requestWorkflowHistorys[$scope.requestWorkflowHistorys.length-1].assignedTo.login;
			                $scope.requestStatus = $scope.requestWorkflowHistorys[$scope.requestWorkflowHistorys.length-1].statusMaster.id;
			                console.log("Request at :"+$scope.requestAt);
			                $scope.requestStatus = $scope.requestWorkflowHistorys[$scope.requestWorkflowHistorys.length-1].statusMaster.id;
			                console.log("status :"+$scope.requestStatus);
			            });
			        };
			        $scope.getWorkflowHistoryByDomainId();

					

					$scope.dtmax = new Date();

					$scope.clear = function() {
						// $uibModalInstance.dismiss('cancel');
						$('#saveSuccessfullyModal').modal('hide');
						$('#declineModal').modal('hide');
						$state.go('customer.pipeSizeList');
					};

					$scope.datePickerForAssignedDate = {};

					$scope.datePickerForAssignedDate.status = {
						opened : false
					};

					$scope.datePickerForAssignedDateOpen = function($event) {
						$scope.datePickerForAssignedDate.status.opened = true;
					};

					
					//approve a request
					$scope.approve = function(customer){						
			        	return $http.post('/api/customers/customersApprove',
								customer).then(
								function(response) {
									console.log("Server response:"
											+ JSON.stringify(response));									
									$('#saveSuccessfullyModal').modal('show');
									//$state.go('request');
									//$state.go('customer.pipeSizeList');
								});
			        }
					
					//declineRequest
					$scope.declineRequest = function(changeCaseDTO){
			        	return $http.post('/api/customers/declineRequest',
			        			changeCaseDTO).then(
								function(response) {
									$('#declineModal').modal('show');
									//$state.go('customer.pipeSizeList');
									//$window.history.back();
								});
			        }
					
					/*$scope.canDecline = function() {
						var ret = false;
						switch ($scope.customer.status) {
						case 'INITIATED':
							if ($scope.orgRole.id === 9) //Technical Manager
								ret = true;
							break;
						case 'PROCESSING':
							if ($scope.orgRole.id === 18) //Billing Officer
								ret = true;
							break;
						default:
							break;
						}
						return ret;
					}*/
				});
