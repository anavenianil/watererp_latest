'use strict';

angular.module('watererpApp').factory(
		'ApplicationTxnService',
		function($http) {
			return {
				approveRequest : function(id, remarks) {
					return $http.get('api/applicationTxns/approveRequest', {
						params : {
							id : id,
							remarks : remarks
						}
					}).then(function(response) {
						return response.data;
					});
				},
				getPendingRequests : function() {
					return $http.get('api/applicationTxns/getPendingRequests')
							.then(function(response) {
								return response.data;
							});
				},
				getApprovedRequests : function() {
					return $http.get('api/applicationTxns/getApprovedRequests')
							.then(function(response) {
								return response.data;
							});
				},
				getRequests : function(type, action_type) {
					return $http.get(
							'api/applicationTxns/getRequests/' + type + '/'
									+ action_type).then(function(response) {
						return response.data;
					});
				},
				approveCustomerComplaints : function(id, remarks) {
					return $http.get(
							'api/customerComplaints/approveCustomerCompaints',
							{
								params : {
									id : id,
									remarks : remarks
								}
							}).then(function(response) {
						return response.data;
					});
				},
				declineRequest : function(id) {
					return $http.get('api/applicationTxns/declineRequest', {
						params : {
							id : id
						}
					}).then(function(response) {
						return response.data;
					});
				},
				getProceedings : function(applicationTxnId) {
					return $http.get('api/proceedingss/custom', {
						params : {
							applicationTxnId : applicationTxnId
						}
					}).then(function(response) {
						return response.data;
					});
				},
				generateCan : function(feasibilityId) {
					return $http.get('api/applicationTxns/can', {
						params : {
							feasibilityId : feasibilityId
						}
					}).then(function(response) {
						return response.data;
					});
				}
			/*
			 * , getMyRequests: function () { return
			 * $http.get('api/applicationTxns/getMyRequests').then(function
			 * (response) { return response.data; }); }
			 */

			/*
			 * , search: function (applicationTxnNo, applicationTxnDt,
			 * proposedDt, statusSearch) { return
			 * $http.get('api/applicationTxns/search', {params:
			 * {applicationTxnNo: applicationTxnNo, applicationTxnDt:
			 * applicationTxnDt, proposedDt: proposedDt, statusSearch:
			 * statusSearch}}).then(function (response) { return response.data;
			 * }); },
			 * 
			 * findByStatus : function(applicationTxnStatus){ return
			 * $http.get('api/applicationTxn/byStatus', {params:
			 * {applicationTxnStatus:applicationTxnStatus}}).then(function(response){
			 * return response.data; }) }
			 */
			};
		});
