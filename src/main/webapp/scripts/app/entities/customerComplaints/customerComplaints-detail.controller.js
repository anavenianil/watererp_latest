'use strict';

angular
		.module('watererpApp')
		.controller(
				'CustomerComplaintsDetailController',
				function($scope, $state, $filter, $rootScope, $stateParams,
						$http, CustomerComplaints, ComplaintTypeMaster,
						Principal, RequestWorkflowHistory, ParseLinks,
						ApplicationTxnService, BillFullDetailsSvc, DateUtils,
						BillFullDetailsBillMonths, BillFullDetails, BillRunDetails) {
					// This code is used to get the role name / designation.
					$scope.orgRole = Principal.getOrgRole();

					$scope.customerComplaints = {};
					$scope.billFullDetailss = [];
					$scope.billRunDetailss = [];
					$scope.can = $scope.customerComplaints.can;
					$scope.customerComplaints.billMonth = new Date();
					// $scope.customerComplaints.can ='';


					$scope.load = function(id) {
						CustomerComplaints
								.get(
										{
											id : id
										},
										function(result) {
											$scope.customerComplaints = result;
											$scope.customerComplaints.remarks1 = $scope.customerComplaints.remarks;
											$scope.customerComplaints.remarks = "";
											$scope.getBillRunDetails($scope.customerComplaints.can,4);
										});
					};

					$scope.getBillById = function(id) {
						BillFullDetails.get({
							id : id
						}, function(result) {
							$scope.billFullDetails = result;

						});
					};

					if ($stateParams.id != null) {
						$scope.load($stateParams.id);
					}

					var unsubscribe = $rootScope.$on(
							'watererpApp:customerComplaintsUpdate', function(
									event, result) {
								$scope.customerComplaints = result;
							});
					$scope.$on('$destroy', unsubscribe);

					var onSaveSuccess = function(result) {
						$scope.isSaving = false;
						$state.go('customerComplaints');
					};

					var onSaveError = function(result) {
						$scope.isSaving = false;
					};
					
					$scope.approve = function(id) {
						// ApplicationTxnService.approveCustComplaint(id);
						if ($scope.customerComplaints.id != null) {
							CustomerComplaints
									.update($scope.customerComplaints, onSaveSuccess, onSaveError);
							$scope.getWorkflowHistoryByDomainId();
						}
					}

					$scope.datePickerForBillMonth = {};

					$scope.datePickerForBillMonth.status = {
						opened : false
					};

					$scope.datePickerForBillMonthOpen = function($event) {
						$scope.datePickerForBillMonth.status.opened = true;
					};

					$scope.getBillDetail = function(billDate) {
						var dateFormat = 'yyyy-MM-dd';
						var billDateISO = $filter('date')(billDate, dateFormat);

						$scope.can = $scope.customerComplaints.can;

						BillFullDetailsSvc.findByCanAndBillDate($scope.can,
								billDateISO).then(function(result) {
							if (result === "error") {
								alert("Bill Not found");
							} else
								$scope.billFullDetails = result;
						});
					}

					$scope.getWorkflowHistoryByDomainId = function() {
						$scope.requestWorkflowHistorys = [];
						RequestWorkflowHistory.query({
							page : $scope.page,
							size : 20,
							dimainObjectId : $stateParams.id,
							requestTypeId : $stateParams.requestTypeId
						}, function(result, headers) {
							$scope.links = ParseLinks.parse(headers('link'));
							for (var i = 0; i < result.length; i++) {
								$scope.requestWorkflowHistorys.push(result[i]);
							}
						});
					};

					if ($stateParams.id != null) {
						$scope.getWorkflowHistoryByDomainId();
					}
					
					$scope.getBillRunDetails = function(can, status) {
			            BillRunDetails.query({page: $scope.page, size: 20, can: can, status:status}, function(result, headers) {
			                $scope.links = ParseLinks.parse(headers('link'));
			                for (var i = 0; i < result.length; i++) {
			                    $scope.billRunDetailss.push(result[i]);
			                }
			            });
			        };
			        
			        $scope.canDecline = function() {
						var ret = false;
						switch ($scope.customerComplaints.status) {
						case 0:
							if ($scope.orgRole.id === 18)
								ret = true;
							break;
						case 1:
							if ($scope.orgRole.id === 11)
								ret = true;
							break;
						case 2:
							if ($scope.orgRole.id === 12)
								ret = true;
							break;
						case 3:
							if ($scope.orgRole.id === 4)
								ret = true;
							break;
						case 4:
							if ($scope.orgRole.id === 16)
								ret = true;
							break;
						default:
							break;
						}
						return ret;
					}
				});
