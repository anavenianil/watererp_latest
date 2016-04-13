'use strict';

angular.module('watererpApp').controller(
		'BillRunDetailsController',
		function($scope, $state, $stateParams, BillRunDetails, BillRunDetailsByRunId, ParseLinks) {

			$scope.billRunDetailss = [];
			$scope.predicate = 'id';
			$scope.reverse = true;
			$scope.page = 0;

			$scope.loadAll = function() {
				
				console.log("This is the stateParam billRunId:" + JSON.stringify($stateParams));
				
				if ($stateParams.billRunId != null && $stateParams.billRunId !== '') {
					BillRunDetailsByRunId.query({
						id: $stateParams.billRunId,
						page : $scope.page,
						size : 20,
						sort : [
								$scope.predicate + ','
										+ ($scope.reverse ? 'asc' : 'desc'),
								'id' ]
					}, function(result, headers) {
						$scope.links = ParseLinks.parse(headers('link'));
						for (var i = 0; i < result.length; i++) {
							$scope.billRunDetailss.push(result[i]);
						}
					});
				} else {
					BillRunDetails.query({
						page : $scope.page,
						size : 20,
						sort : [
								$scope.predicate + ','
										+ ($scope.reverse ? 'asc' : 'desc'),
								'id' ]
					}, function(result, headers) {
						$scope.links = ParseLinks.parse(headers('link'));
						for (var i = 0; i < result.length; i++) {
							$scope.billRunDetailss.push(result[i]);
						}
					});
				}
			};

			$scope.reset = function() {
				$scope.page = 0;
				$scope.billRunDetailss = [];
				$scope.loadAll();
			};

			$scope.loadPage = function(page) {
				$scope.page = page;
				$scope.loadAll();
			};

			$scope.loadAll();

			$scope.refresh = function() {
				$scope.reset();
				$scope.clear();
			};

			$scope.clear = function() {
				$scope.billRunDetails = {
					can : null,
					fromDt : null,
					toDt : null,
					status : null,
					remarks : null,
					id : null
				};
			};
		});
