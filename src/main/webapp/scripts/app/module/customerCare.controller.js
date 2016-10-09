/*'use strict';

angular.module('watererpApp').controller('CustomerCareController',
		function($scope, $state, $rootScope, Account, User, Principal) {
			$state.go('customerComplaints');
		});
*/

'use strict';

angular.module('watererpApp').controller(
		'CustomerCareController',
		function($scope, $state, $rootScope, Account, User,
				ApplicationTxnService, Principal, $stateParams) {
			$scope.pendingRequests = [];
			$scope.approvedRequests = [];
			$scope.myRequests = [];

			Principal.hasAuthority("ROLE_CUSTOMER").then(function(result) {
				console.log("This is the result:" + JSON.stringify(result));
				if (result) {
					$state.go('customerComplaints.new');
				}
			});
			
			Principal.hasAuthority("ROLE_GIS").then(function(result) {
				console.log("This is the result:" + JSON.stringify(result));
				if (result) {
					$state.go('waterLeakageComplaint');
				}
			});

			$scope.loadAll = function() {
				ApplicationTxnService.getPendingRequests($stateParams.id).then(function(data) {
					$scope.pendingRequests = data;
				});

				ApplicationTxnService.getApprovedRequests($stateParams.id).then(
						function(data) {
							$scope.approvedRequests = data;
						});

				/*ApplicationTxnService.getMyRequests().then(function(data) {
					$scope.myRequests = data;
				});*/
			}

			Principal.identity().then(function(account) {
				$scope.account = account;
				$scope.isAuthenticated = Principal.isAuthenticated;

				if (!$scope.isAuthenticated())
					$state.go('login');

				else {
					User.get({
						login : $scope.account.login
					}, function(result) {
						$scope.user = result;
						$scope.loadAll();
					});
					
				}
			});

			/*$scope.getDetails = function(type) {
				if (type === 'REQUISITION' || type === 'WITHOUTMETER') {
					$state.go('applicationTxn');
				}
				if (type === 'METER CHANGE'){
					$state.go('meterChange');
				}
				if (type === 'CONNECTION CATEGORY'){
					$state.go('workflowTxnDetails');
				}
			}*/
		});
