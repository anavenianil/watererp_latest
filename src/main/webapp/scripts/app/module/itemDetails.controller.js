'use strict';

angular.module('watererpApp').controller('ItemDetailsController',
		function($scope, $state, $rootScope, Account, User, Principal) {
			$state.go('materialMaster');
		});
