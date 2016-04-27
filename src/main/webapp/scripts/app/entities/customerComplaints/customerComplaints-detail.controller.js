'use strict';

angular.module('watererpApp').controller(
		'CustomerComplaintsDetailController',
		function($scope, $filter, $rootScope, $stateParams, 
				CustomerComplaints, ComplaintTypeMaster,
				RequestWorkflowHistory, ParseLinks, ApplicationTxnService,
				BillFullDetailsSvc, DateUtils, BillFullDetailsBillMonths, BillFullDetails) {
			
			$scope.customerComplaints = {};
			$scope.billFullDetailss = [];
			$scope.can = $scope.customerComplaints.can;
			$scope.customerComplaints.billMonth = new Date();
			//$scope.customerComplaints.can ='';

			$scope.getBillDetails = function(can) {
	            BillFullDetails.query({page: $scope.page, size: 20, can:can}, function(result, headers) {
	                $scope.links = ParseLinks.parse(headers('link'));
	                for (var i = 0; i < result.length; i++) {
	                    $scope.billFullDetailss.push(result[i]);
	                }
	            });
	        };
			$scope.load = function(id) {
				CustomerComplaints.get({
					id : id
				}, function(result) {
					$scope.customerComplaints = result;
					$scope.getBillDetails($scope.customerComplaints.can);
					
				});
			};
				
			$scope.getBillById = function (id) {
	            BillFullDetails.get({id: id}, function(result) {
	                $scope.billFullDetails = result;
	            });
	        };
						
			if($stateParams.id != null){
				$scope.load($stateParams.id);
			}
			
			var unsubscribe = $rootScope.$on(
					'watererpApp:customerComplaintsUpdate', function(event,
							result) {
						$scope.customerComplaints = result;
					});
			$scope.$on('$destroy', unsubscribe);

			/*$scope.getWorkflowHistoryByDomainId = function() {
				$scope.requestWorkflowHistorys = [];
				RequestWorkflowHistory.query({
					page : $scope.page,
					size : 20,
					dimainObjectId : $stateParams.id
				}, function(result, headers) {
					$scope.links = ParseLinks.parse(headers('link'));
					for (var i = 0; i < result.length; i++) {
						$scope.requestWorkflowHistorys.push(result[i]);
					}
				});
			};*/
			
			
			

			$scope.approve = function(id) {
				ApplicationTxnService.approveCustComplaint(id);
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

				console.log(billDateISO);
				$scope.can = $scope.customerComplaints.can;

				BillFullDetailsSvc
						.findByCanAndBillDate($scope.can, billDateISO).then(
								function(result) {
									if (result === "error") {
										alert("Bill Not found");
									} else
										$scope.billFullDetails = result;
								});
			}
			
			$scope.getWorkflowHistoryByDomainId = function() {
	        	$scope.requestWorkflowHistorys = [];
	            RequestWorkflowHistory.query({page: $scope.page, size: 20, dimainObjectId: $stateParams.id, requestId: 3}, function(result, headers) {
	                $scope.links = ParseLinks.parse(headers('link'));
	                for (var i = 0; i < result.length; i++) {
	                    $scope.requestWorkflowHistorys.push(result[i]);
	                }
	            });
	        };
	        
	        if ($stateParams.id != null) {
				$scope.getWorkflowHistoryByDomainId();
			}

		});
