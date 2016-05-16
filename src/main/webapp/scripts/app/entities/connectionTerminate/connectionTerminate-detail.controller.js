'use strict';

angular.module('watererpApp')
    .controller('ConnectionTerminateDetailController', function ($scope, $rootScope, $stateParams, entity, ConnectionTerminate, 
    		MeterDetails, RequestWorkflowHistory, ParseLinks, CustDetailsSearchCAN, Principal, $http, $window) {
        $scope.connectionTerminate = entity;
        $scope.workflowDTO = {};
        $scope.workflowDTO.connectionTerminate = entity;
        $scope.custDetails = {};
        
        $scope.orgRole = Principal.getOrgRole();
        $scope.load = function (id) {
            ConnectionTerminate.get({id: id}, function(result) {
                $scope.connectionTerminate = result;
            });
        };
        
        var unsubscribe = $rootScope.$on('watererpApp:connectionTerminateUpdate', function(event, result) {
            $scope.connectionTerminate = result;
        });
        $scope.$on('$destroy', unsubscribe);

        $scope.getWorkflowHistoryByDomainId = function() {
        	$scope.requestWorkflowHistorys = [];
            RequestWorkflowHistory.query({page: $scope.page, size: 20, dimainObjectId: $stateParams.id, requestTypeId: $stateParams.requestTypeId}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.requestWorkflowHistorys.push(result[i]);
                }
            });
        };
        $scope.getWorkflowHistoryByDomainId();
        
        $scope.getCustDetails = function(can) {
			CustDetailsSearchCAN.get({can : can},function(result) {
								$scope.custDetails = result;
							});
		};
		$scope.getCustDetails($scope.connectionTerminate.can);
		
		$scope.datePickerForMeterRecoveredDate = {};

        $scope.datePickerForMeterRecoveredDate.status = {
            opened: false
        };

        $scope.datePickerForMeterRecoveredDateOpen = function($event) {
            $scope.datePickerForMeterRecoveredDate.status.opened = true;
        };
        
		$scope.canDecline = function() {
			var ret = false;
			switch ($scope.custDetails.status) {
			case "PROCESSING":
				if ($scope.orgRole.id === 10)
					ret = true;
				break;
			default:
				break;
			}
			return ret;
		}
		
		
		$scope.approve = function(workflowDTO){
        	return $http.post('/api/connectionTerminates/approve',
					workflowDTO).then(
					function(response) {
						console.log("Server response:"
								+ JSON.stringify(response));
						$window.history.back();
					});
        }
		
		
		$scope.declineRequest = function(meterChange){
        	return $http.post('/api/connectionTerminates/declineRequest',
					workflowDTO ).then(
					function(response) {
						console.log("Server response:"
								+ JSON.stringify(response));
						$window.history.back();
					});
        }
		
    });
