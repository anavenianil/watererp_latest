'use strict';

angular
		.module('watererpApp')
		.controller(
				'CustomerPipeSizeChangeDetailController',
				function($scope, $stateParams, CustDetails,
						$state, $http, ParseLinks, RequestWorkflowHistory, Customer, CustDetailsSearchCAN, Principal) {

					$scope.customer = {};
					$scope.customer.changeType = "PIPESIZE";
					
					$scope.custDetails = {};
					$scope.orgRole = Principal.getOrgRole();
					
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

					
					//approve a request
					$scope.approve = function(customer){
			        	//console.log(customer);
			        	return $http.post('/api/customersApprove',
								customer).then(
								function(response) {
									console.log("Server response:"
											+ JSON.stringify(response));
									$state.go('request');
								});
			        }
				});
