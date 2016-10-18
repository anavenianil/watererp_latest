'use strict';

angular
		.module('watererpApp')
		.controller(
				'SewerageRequestDetailController',
				function($scope, $rootScope, $stateParams, entity,
						SewerageRequest, TariffCategoryMaster, Receipt, RequestWorkflowHistory) {
        $scope.sewerageRequest = entity;
        $scope.load = function (id) {
            SewerageRequest.get({id: id}, function(result) {
                $scope.sewerageRequest = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:sewerageRequestUpdate', function(event, result) {
            $scope.sewerageRequest = result;
        });
        $scope.$on('$destroy', unsubscribe);
        
        $scope.getWorkflowHistoryByDomainId = function(requestTypeId) {
        	$scope.requestWorkflowHistorys = [];
            RequestWorkflowHistory.query({page: $scope.page, size: 20, dimainObjectId: $stateParams.id, requestTypeId: $stateParams.requestTypeId}, function(result, headers) {
                for (var i = 0; i < result.length; i++) {
                    $scope.requestWorkflowHistorys.push(result[i]);
                }
                $scope.requestAt = $scope.requestWorkflowHistorys[$scope.requestWorkflowHistorys.length-1].assignedTo.login;
                $scope.requestStatus = $scope.requestWorkflowHistorys[$scope.requestWorkflowHistorys.length-1].statusMaster.id;
                console.log("Request at :"+$scope.requestAt);
                $scope.requestStatus = $scope.requestWorkflowHistorys[$scope.requestWorkflowHistorys.length-1].statusMaster.id;
                console.log("status :"+$scope.requestStatus);
            });
        };
        
        $scope.getWorkflowHistoryByDomainId();

    });
