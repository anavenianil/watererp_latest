'use strict';

angular.module('watererpApp').controller('OnlinePaymentController',
		function($scope, $state, $rootScope, Account, User, Principal) {
			$state.go('onlinePaymentOrder');
		});
