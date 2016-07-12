'use strict';

angular.module('watererpApp')
    .controller('ConnectionTerminateDetailController', function ($scope, $rootScope, $stateParams, ConnectionTerminate, 
    		MeterDetails, RequestWorkflowHistory, ParseLinks, CustDetailsSearchCAN, Principal, $http, $window, $state) {
        $scope.connectionTerminate = {};
        $scope.changeCaseDTO = {};
        $scope.custDetails = {};
        $scope.maxDt = new Date();
        Principal.getOrgRole().then(function(response) {
			$scope.orgRole = response;
		});
        
		
		$scope.getCustDetails = function(can) {
			CustDetailsSearchCAN.get({can : can}, function(result) {
                $scope.custDetails = result;
            });
        };
		
        $scope.load = function (id) {
            ConnectionTerminate.get({id: id}, function(result) {
                $scope.connectionTerminate = result;
                $scope.changeCaseDTO.connectionTerminate = result;
                $scope.getCustDetails($scope.connectionTerminate.can);
            });
        };
        if($stateParams.id != null){
        	$scope.load($stateParams.id)
        }
        
        
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
        
        
		$scope.datePickerForMeterRecoveredDate = {};

        $scope.datePickerForMeterRecoveredDate.status = {
            opened: false
        };

        $scope.datePickerForMeterRecoveredDateOpen = function($event) {
            $scope.datePickerForMeterRecoveredDate.status.opened = true;
        };
        
        $scope.datePickerForApprovedDate = {};

        $scope.datePickerForApprovedDate.status = {
            opened: false
        };

        $scope.datePickerForApprovedDateOpen = function($event) {
            $scope.datePickerForApprovedDate.status.opened = true;
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
		
		$scope.canShow = function() {
			var ret = false;
			switch ($scope.custDetails.status) {
			case "TERMINATED":
					$scope.status = 9;
					ret = true;
					break;
			default:
				break;
			}
			return ret;
		}
		
		$scope.approve = function(changeCaseDTO){
        	return $http.post('/api/connectionTerminates/approve',
        			changeCaseDTO).then(
					function(response) {
						$state.go('connectionTerminate');
					});
        }
		
		
		$scope.declineRequest = function(changeCaseDTO){
        	return $http.post('/api/connectionTerminates/declineRequest',
        			changeCaseDTO ).then(
					function(response) {
						$window.history.back();
					});
        }
		
		$scope.checkReading = function(prevReding, newReading){
			if(prevReding > newReading){
				alert("New reading should be greater than previous reading");
				$scope.changeCaseDTO.connectionTerminate.lastMeterReading="";
			}
		}
    });
