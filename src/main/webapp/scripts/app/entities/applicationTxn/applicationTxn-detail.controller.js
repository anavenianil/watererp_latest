'use strict';

angular.module('watererpApp').controller(
		'ApplicationTxnDetailController',
		function($state, $scope, $rootScope, $stateParams, entity,
				ApplicationTxn, ApplicationTxnService, RequestWorkflowHistory,
				ParseLinks, Principal) {
			$scope.applicationTxn = entity;
			
			$scope.orgRole = {};

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
				switch ($scope.applicationTxn.status) {
				case 0:
					if ($scope.orgRole.orgRoleName === 'Technical Manager')
						ret = true;
					break;
				case 1:
					if ($scope.orgRole.orgRoleName === 'Technical Zonal Supervisor')
						ret = true;
					break;
				case 2:
					if ($scope.orgRole.orgRoleName === 'Officer, Operation & Maintance - NRW, Water Supply and Sanitation')
						ret = true;
					break;
				case 3:
					if ($scope.orgRole.orgRoleName === 'Technical Manager')
						ret = true;
					break;
				case 4:
					if ($scope.orgRole.orgRoleName === 'Assistant Accountant(Revenue)')
						ret = true;
					break;
				case 5:
					if ($scope.orgRole.orgRoleName === 'Stores & Supplies Officer')
						ret = true;
					break;
				case 6:
					if ($scope.orgRole.orgRoleName === 'Officer, Operation & Maintance - NRW, Water Supply and Sanitation')
						ret = true;
					break;
				case 7:
					if ($scope.orgRole.orgRoleName === 'Billing Officer')
						ret = true;
					break;
				case 8:
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
