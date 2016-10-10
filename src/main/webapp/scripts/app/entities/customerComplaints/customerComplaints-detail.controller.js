'use strict';

angular
		.module('watererpApp')
		.controller(
				'CustomerComplaintsDetailController',
				function($scope, $state, $filter, $rootScope, $stateParams,
						$http, $window, CustomerComplaints, ComplaintTypeMaster,
						Principal, RequestWorkflowHistory, ParseLinks,
						ApplicationTxnService, BillFullDetailsSvc, DateUtils,
						BillFullDetailsBillMonths, BillFullDetails, BillRunDetails, TariffCategoryMaster, CustDetailsSearchCAN, MaterialMaster, Uom, DivisionMaster, StreetMaster) {
					// This code is used to get the role name / designation.
					//$scope.orgRole = Principal.getOrgRole();
					$scope.orgRole = {};
					
					//$scope.leakageType = "BURST";
					//$scope.leakageType = "VALVE";
					//$scope.leakageType = "HYDRANT";
					
					$scope.materialmasters = MaterialMaster.query();
			        $scope.uoms = Uom.query();
					
					Principal.getOrgRole().then(function(response) {
						$scope.orgRole = response;
					});

					$scope.customerComplaints = {};
					$scope.billFullDetailss = [];
					$scope.billRunDetailss = [];
					$scope.month = [];
					var len = $scope.billRunDetailss.length;
					console.log(len);
					$scope.can = $scope.customerComplaints.can;
					$scope.customerComplaints.billMonth = new Date();
					// $scope.customerComplaints.can ='';

					$scope.load = function(id) {
						CustomerComplaints.get({
							id : id
						}, function(result) {
							$scope.customerComplaints = result;
							$scope.customerComplaints.remarks1 = $scope.customerComplaints.remarks;
							$scope.customerComplaints.remarks = "";
							$scope.getBillRunDetails($scope.customerComplaints.can,4);
						});
					};

					$scope.getBillById = function(id) {
						$scope.customerComplaints.adjustmentBillId = id;
						BillFullDetails.get({
							id : id
						}, function(result) {
							$scope.billFullDetails = result;
							$scope.customerComplaints.adjustmentBillId = $scope.billFullDetails.id;
		                	$scope.customerComplaints.billFullDetails = $scope.billFullDetails;

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
						//$state.go('customerComplaints');
						$window.history.back();
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
						$scope.getCustDetails(can);
			            BillRunDetails.query({page: $scope.page, size: 20, can: can, status:status}, function(result, headers) {
			                $scope.links = ParseLinks.parse(headers('link'));
			                for (var i = 0; i < result.length; i++) {
			                	console.log(result.length);
			                    $scope.billRunDetailss.push(result[i]);
			                    $scope.billFullDetailss.push(result[i].billFullDetails);
			                    var d = new Date(result[i].billFullDetails.fromMonth.substr(0, 4)+'-'+result[i].billFullDetails.fromMonth.substr(4, 2));
			                    var d1 = new Date(result[i].billFullDetails.toMonth.substr(0, 4)+'-'+result[i].billFullDetails.toMonth.substr(4, 2));
			                    $scope.month[0] = "Jan";
			                    $scope.month[1] = "Feb";
			                    $scope.month[2] = "Mar";
			                    $scope.month[3] = "Apr";
			                    $scope.month[4] = "May";
			                    $scope.month[5] = "Jun";
			                    $scope.month[6] = "Jul";
			                    $scope.month[7] = "Aug";
			                    $scope.month[8] = "Sep";
			                    $scope.month[9] = "Oct";
			                    $scope.month[10] = "Nov";
			                    $scope.month[11] = "Dec";
			                    result[i].billFullDetails.fromMonth = $scope.month[d.getMonth()]+" "+result[i].billFullDetails.fromMonth.substr(0,4);
			                    result[i].billFullDetails.toMonth = $scope.month[d1.getMonth()]+" "+result[i].billFullDetails.toMonth.substr(0,4);
			                }
			                if($scope.customerComplaints.adjustmentBillId != null){
			                	$scope.getBillById($scope.customerComplaints.adjustmentBillId);
			                	/*$scope.customerComplaints.adjustmentBillId = $scope.billFullDetails.id;
			                	$scope.customerComplaints.billFullDetails.netPayableAmount = $scope.billFullDetails.netPayableAmount;*/
			                	
			                }
			            });
			        };
			        
			        $scope.getCustDetails = function(can) {
						CustDetailsSearchCAN.get({can : can}, function(result) {
			                $scope.custDetails = result;
			            });
			        };    
			        
			        $scope.canDecline = function() {
						var ret = false;
						switch ($scope.customerComplaints.status) {
						case 0:
							if ($scope.orgRole.id === 33)	//Customer Relation Officer Head
								ret = true;
							break;
						case 1:
							if ($scope.orgRole.id === 10 || $scope.orgRole.id === 9)	//Commercial Manager || Technical Manager
								ret = true;
							break;
						case 2:
							if ($scope.orgRole.id === 11)	//Finance Manager || Technical Zonal Supervisor
								ret = true;
							break;
						case 3:
							if ($scope.orgRole.id === 4 || $scope.orgRole.id === 9)	//Managing Director || Technical Manager
								ret = true;
							break;
						case 4:
							if ($scope.orgRole.id === 18)	//Billing Officer
								ret = true;
							break;
						default:
							break;
						}
						return ret;
					}
			        
			      //create array for items
			        $scope.count = 0;
			        //$scope.customerComplaints.itemRequireds = [];
			        $scope.createItemArr = function(){
			        	$scope.customerComplaints.itemRequireds = [];
			       		$scope.customerComplaints.itemRequireds[$scope.count]= {};
			       		$scope.count = $scope.count +1;
			        }
			        
			      //for removing items
			        $scope.removeItemArr = function(indexId) {
			            $scope.count = $scope.count -1;
			            $scope.customerComplaints.itemRequireds.splice(indexId, 1);
			          };
			          
			          $scope.countValve = 0;
			          //for valve
			          //$scope.customerComplaints.valveRequests = [];
			          $scope.createValveArr = function(){
			        	  	$scope.customerComplaints.valveRequests = [];
				       		$scope.customerComplaints.valveRequests[$scope.countValve]= {};
				       		$scope.countValve = $scope.countValve +1;
				        }
			          
			          $scope.removeValveArr = function(indexId) {
				            $scope.countValve = $scope.countValve -1;
				            $scope.customerComplaints.valveRequests.splice(indexId, 1);
				          };

				
				});
