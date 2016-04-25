'use strict';

angular.module('watererpApp').controller('CustomerCareController',
		function($scope, $state, $rootScope, Account, User, Principal) {
			$state.go('customerComplaints');
		});
