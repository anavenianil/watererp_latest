'use strict';

angular.module('watererpApp').controller(
		'RequestController',
		function($scope, $stateParams, $state, ApplicationTxnService) {
			$scope.requests = [];
			$scope.action_type = '';

			$scope.load = function(type,action_type) {
				$scope.action_type = action_type;

					ApplicationTxnService.getRequests(type,action_type).then(
							function(data) {
								$scope.requests = data;
							});
			};

			$scope.load($stateParams.type, $stateParams.action_type);
			
			
			$scope.getDetails = function(requestId, requestType){
				console.log("request Id:" +requestId);
				console.log("request Type: "+requestType);
				if(requestType===8){//CONNECTION CATEGORY
					$state.go('custDetails.categoryChangeEdit',{requestId:requestId});
				}
			}

		});
