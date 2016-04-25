'use strict';

angular.module('watererpApp').controller('BillCycleRunController',
		function($scope, $state, $rootScope, Account, User, Principal) {
			$state.go('billRunMaster');
		});
