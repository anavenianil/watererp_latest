'use strict';

angular.module('watererpApp').controller(
		'ApplicationTxnDetailController',
		function($state, $scope, $rootScope, $stateParams, entity,
				ApplicationTxn, ApplicationTxnService, RequestWorkflowHistory,
				ParseLinks, Principal, EmpRoleMapping) {
			$scope.applicationTxn = entity;
			
			$scope.orgRole = {};
			/*var onSaveSuccess = function (response) {
				console.log("Show Button"+JSON.stringify(response));
	        };
			
			$scope.empRoleWorkflow = EmpRoleMapping.getMappingsByWorkflow({domainObjectId:$stateParams.id, requestTypeId: $stateParams.requestTypeId});
			$scope.empLogin = EmpRoleMapping.getMappingsByLogin();*/

			//$scope.orgRole = Principal.getOrgRole();
			
			Principal.getOrgRole().then(function(response) {
				$scope.orgRole = response;
			});
			
			$scope.approvalDetails = {};

			$scope.maxDate = new Date();

			$scope.load = function(id) {
				ApplicationTxn.get({
					id : id
				}, function(result) {
					$scope.applicationTxn = result;
				});
			};

			var unsubscribe = $rootScope.$on(
					'watererpApp:applicationTxnUpdate',
					function(event, result) {
						$scope.applicationTxn = result;
					});
			$scope.$on('$destroy', unsubscribe);

			//$scope.status = null;
			$scope.getApplicationTxn = function(id) {
				$scope.approvalDetails.approvedDate = new Date();
				$('#approveModal').modal('show');
				$scope.load(id);
			};

			console.log("request type id is: " + $stateParams.requestTypeId);

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
			//$scope.getWorkflowHistoryByDomainId();

			$scope.canDecline = function() {
				var ret = false;
				/*console.log("$scope.orgRole.id:"+$scope.orgRole.id);
				console.log("$scope.applicationTxn.status:"+$scope.applicationTxn.status);*/
				switch ($scope.applicationTxn.status) {
				case 0:
					if ($scope.orgRole.id === 14) //Water Network Engineer
						ret = true;
					break;
				case 1:
					if ($scope.orgRole.id === 9) //Technical Manager
						ret = true;
					break;
				case 2:
					if ($scope.orgRole.id === 4) //Managing Director 
						ret = true;
					break;
				case 3:
					if ($scope.orgRole.id === 26) //Cashier
						ret = true;
					break;
				case 4:
					if ($scope.orgRole.id === 25) //Stores & Supplies Officer
						ret = true;
					break;
				case 5:
					if ($scope.orgRole.id === 19) //Credit Control Officer(For Meter)
						ret = true;
					break;
				case 6:
					if ($scope.orgRole.id === 14) //Water Network Engineer
						ret = true;
					break;
				case 7:
					if ($scope.orgRole.id === 18) //Billing Officer
						ret = true;
					break;
				default:
					break;

				}
				return ret;
			}

			$scope.approvalDetailsSave = function(id, remarks, approvedDate) {
				$('#approveModal').modal('hide');
				ApplicationTxnService.approveRequest(id, remarks, approvedDate).then(function(data) {
					$scope.applicationTxn = data;
					$state.go('applicationTxn');
				});
			}

			// to decline a request
			$scope.declineRequest = function(id) {
				ApplicationTxnService.declineRequest(id).then(function(data) {
					$scope.applicationTxn = data;
					$state.go('applicationTxn');
				});
			};

			$scope.datePickerForApprovedDate = {};

			$scope.datePickerForApprovedDate.status = {
				opened : false
			};

			$scope.datePickerForApprovedDateOpen = function($event) {
				$scope.datePickerForApprovedDate.status.opened = true;
			};

		});
