'use strict';

angular.module('watererpApp').controller('FooterController',
		function($scope, $state, $rootScope, AccountService) {
			$scope.version = {};
			AccountService.getVersion().then(function(response)
					{
						$scope.version = response;
					}
					);
		});
