'use strict';

angular.module('watererpApp').controller(
		'DashboardController',
		function($scope, $state, $rootScope, Account, User, $location,
				 Auth, Principal) {
			$scope.pendingRequests = [];
			$scope.approvedRequests = [];
			$scope.myRequests = [];
			$scope.$state = $state;
			$scope.isAuthenticated = Principal.isAuthenticated;
			$scope.module2menu_items = {};

			$scope.myLogout = function() {
				Auth.logout();
				window.location = '/';
			};

			$scope.getLogin = function() {
				$scope.user = Principal.getLogonUser();
				//console.log("User is: "+JSON.stringify($scope.user));
				if ($scope.user == null) {
					$scope.navbarUserId = "";
				} else {
					$scope.navbarUserId = $scope.user.firstName + " "
							+ $scope.user.lastName + "(" + $scope.user.login
							+ ")";
				}
				//for navbar module and menu_items
				if ($scope.isAuthenticated()) {
					$scope.module2menu_items = Principal.geModuleMenus();
				} else
					$scope.module2menu_items = {};

				return $scope.isAuthenticated();
			}

			//added by mohib to display names in navar.html
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
