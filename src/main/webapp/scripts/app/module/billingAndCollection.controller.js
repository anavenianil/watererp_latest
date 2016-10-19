'use strict';

angular.module('watererpApp').controller('BillingAndCollectionController',
		function($scope, $state, $rootScope, Account, User, Principal) {
			$state.go('collDetails');
		});
