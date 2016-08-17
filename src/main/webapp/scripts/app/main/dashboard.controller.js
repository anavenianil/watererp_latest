'use strict';

angular.module('watererpApp').controller(
		'DashboardController',
		function($scope, $state, $rootScope, Module, Account, User, $location,
				$http, Auth, Principal) {
			//$scope.pendingRequests = [];
			//$scope.approvedRequests = [];
			//$scope.myRequests = [];
			$scope.$state = $state;
			$scope.isAuthenticated = Principal.isAuthenticated;
			$scope.module2menu_items = {};
			$scope.orgRole = {};
			
			if ($scope.isAuthenticated()) {
				Principal.getModuleMenus().then(function(response) {
					$scope.module2menu_items = response;
				});
				
				
				Principal.getOrgRole().then(function(response) {
					$scope.orgRole = response;
				});
			}

			$scope.myLogout = function() {
				Auth.logout();
				window.location = '/';
			};

			$scope.getLogin = function() {
				$scope.user = Principal.getLogonUser();
				// console.log("User is: "+JSON.stringify($scope.user));
				// console.log("User is: "+JSON.stringify($scope.user));
				if ($scope.user == null) {
					$scope.navbarUserId = "";
					$scope.userRole = "";
				} else {
					$scope.navbarUserId = $scope.user.firstName + " "
							+ $scope.user.lastName + "("
							+ $scope.user.login + ")";
					$scope.userRole = $scope.orgRole.orgRoleName;
				}

				return $scope.isAuthenticated();
			}

			// added by mohib to display names in navar.html
			Account.get(function(result) {
				$scope.account = result.data;
				User.get({
					login : $scope.account.login
				}, function(result) {
					$scope.user = result;
				});
			});

			$scope.loadAll = function() {

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

		});
