'use strict';

angular.module('watererpApp').controller('BillingController',
		function($scope, $state, $rootScope, Account, User, Principal) {

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
