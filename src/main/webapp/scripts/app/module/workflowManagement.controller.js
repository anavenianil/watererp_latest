'use strict';

angular.module('watererpApp').controller('WorkflowManagementController',
		function($scope, $state, $rootScope, Account, User, Principal) {
			$state.go('workflow.new');
		});
