'use strict';

angular.module('watererpApp').controller('EmployeeManagementController',
		function($scope, $state, $rootScope, Account, User, Principal) {
			$state.go('user-management');
		});
