'use strict';

angular.module('watererpApp').controller('MainController',
		function($scope, $state, $rootScope, Account, User, ApplicationTxnService, Principal) {
			$scope.pendingRequests = [];
			$scope.approvedRequests = [];
			$scope.myRequests = [];
	        			
			$scope.loadAll = function() {

				ApplicationTxnService.getPendingRequests().then(function(data) {
					$scope.pendingRequests = data;
				});

				ApplicationTxnService.getApprovedRequests().then(function(data) {
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
						
					});
					$scope.loadAll();
									
				}

			});
			
			$scope.getDetails = function(type) {
				console.log(type);
				
				if(type==='REQUISITION')
				{ 
					$state.go('applicationTxn'); 
				}
				else if(type==='INCORRECT BILL' || type==='WATER LEAKAGE'){
					$state.go('customerComplaints'); 
				}
			}
			
		});










/*'use strict';

angular.module('watererpApp')
    .controller('MainController', function ($scope, Principal) {
        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
        });
    });
*/