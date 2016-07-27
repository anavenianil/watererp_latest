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
			
			
			$scope.getDetails = function(requestId, requestTypeId, domainObjectId){
				/*console.log("request Id:" +requestId);
				console.log("request Type: "+requestTypeId);
				console.log("domainObject: "+domainObjectId);*/
				if(requestTypeId===1 || requestTypeId===6){//NEW CONNECTION
					$state.go('applicationTxn.detail',{id:domainObjectId, requestTypeId:requestTypeId});
				}
				if(requestTypeId===8){//CONNECTION CATEGORY CHANGE
					//$state.go('custDetails.categoryChangeEdit',{requestId:requestId, requestTypeId:requestTypeId});
					$state.go('customer.categoryChangeDetail',{id:domainObjectId, requestTypeId:requestTypeId});
				}
				if(requestTypeId===9){//PIPE SIZE CHANGE
					$state.go('customer.pipeSizeChangeDetail',{id:domainObjectId, requestTypeId:requestTypeId});
				}
				if(requestTypeId===10){//NAME CHANGE
					$state.go('customer.nameChangeDetail',{id:domainObjectId, requestTypeId:requestTypeId});
				}
				if(requestTypeId===4 || requestTypeId===3){//CONNECTION CATEGORY
					$state.go('customerComplaints.detail',{id:domainObjectId, requestTypeId:requestTypeId});
				}
				if(requestTypeId===7){//METER CHANGE
					$state.go('meterChange.detail',{id:domainObjectId, requestTypeId:requestTypeId});
				}
				if(requestTypeId===11){//CONNECTION TERMINATION
					$state.go('connectionTerminate.detail',{id:domainObjectId, requestTypeId:requestTypeId});
				}
			}

		});
