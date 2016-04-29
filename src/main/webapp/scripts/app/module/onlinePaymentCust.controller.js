	'use strict';

angular.module('watererpApp').controller('OnlinePaymentCustController',
		function($scope, $state, $rootScope) {
			$state.go('onlinePaymentOrder.new');
		});
