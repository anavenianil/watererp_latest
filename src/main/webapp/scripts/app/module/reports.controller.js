'use strict';

angular.module('watererpApp').controller('ReportsController',
		function($scope, $state, $rootScope, Account, User, Principal) {
			$state.go('custDetailsReport');
		});
