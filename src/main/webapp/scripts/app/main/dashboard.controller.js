'use strict';

angular.module('watererpApp').controller(
		'DashboardController',
		function($scope, $state, $rootScope, Module, Account, User, $location,
				$http, Auth, Principal) {
			$scope.pendingRequests = [];
			$scope.approvedRequests = [];
			$scope.myRequests = [];
			$scope.$state = $state;
			$scope.isAuthenticated = Principal.isAuthenticated;
			Principal.getModuleMenus().then(
					function(response) {
						$scope.module2menu_items = response;
					});

			$scope.myLogout = function() {
				Auth.logout();
				window.location = '/';
			};

			$scope.getLogin = function() {
				$scope.user = Principal.getLogonUser();
				// console.log("User is: "+JSON.stringify($scope.user));
				if ($scope.user == null) {
					$scope.navbarUserId = "";
				} else {
					$scope.navbarUserId = $scope.user.firstName + " "
							+ $scope.user.lastName + "(" + $scope.user.login
							+ ")";
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
