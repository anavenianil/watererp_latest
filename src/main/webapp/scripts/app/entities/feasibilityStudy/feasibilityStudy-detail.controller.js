'use strict';

angular.module('watererpApp').controller(
		'FeasibilityStudyDetailController',
		function($scope, $rootScope, $stateParams, FeasibilityStudy, DivisionMaster, StreetMaster, ApplicationTxn, User) {
			
			$scope.feasibilityStudy = {};
			
			$scope.load = function(id) {
				FeasibilityStudy.get({
					id : id
				}, function(result) {
					$scope.feasibilityStudy = result;
				});
			};
			var unsubscribe = $rootScope.$on(
					'watererpApp:feasibilityStudyUpdate', function(event,
							result) {
						$scope.feasibilityStudy = result;
					});
			$scope.$on('$destroy', unsubscribe);

			if($stateParams.applicationTxnId != null){
				console.log("ApplicationTxnId is:"+$stateParams.applicationTxnId);
				$scope.feasibilityStudy = FeasibilityStudy.getByapplicationTxnId({applicationTxnId:$stateParams.applicationTxnId});
			}
			
		});
