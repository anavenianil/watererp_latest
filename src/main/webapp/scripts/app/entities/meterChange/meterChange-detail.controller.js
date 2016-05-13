'use strict';

angular.module('watererpApp')
    .controller('MeterChangeDetailController', function ($scope, $rootScope, $stateParams, MeterChange, CustDetails, MeterDetails, User,
    		ApplicationTxnService, RequestWorkflowHistory, ParseLinks, $state, Principal, $http, $window) {
        $scope.meterChange = {};
        
        $scope.orgRole = Principal.getOrgRole();
        $scope.users = User.query();
        
        $scope.maxDt= new Date(); 
        
        $scope.load = function (id) {
            MeterChange.get({id: id}, function(result) {
                $scope.meterChange = result;
                $scope.meterChange.remarks = "";
            });
        };
        
        if($stateParams.id != null){
        	$scope.load($stateParams.id);
        }
        
        $scope.loadAllUnallottedMeter = function() {
    		$scope.meterdetailss = [];
            MeterDetails.query({page: $scope.page, size: 20, meterStatusId: 2}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.meterdetailss.push(result[i]);
                }
            });
        };
        
        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:meterChangeUpdate', result);
            //$uibModalInstance.close(result);
            $scope.isSaving = false;
            $state.go('meterChange');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.meterChange.id != null) {
                MeterChange.update($scope.meterChange, onSaveSuccess, onSaveError);
            }
        };
        
        $scope.loadAllUnallottedMeter();
        
        
        $scope.datePickerForApprovedDate = {};

        $scope.datePickerForApprovedDate.status = {
            opened: false
        };

        $scope.datePickerForApprovedDateOpen = function($event) {
            $scope.datePickerForApprovedDate.status.opened = true;
        };
        
        
        var unsubscribe = $rootScope.$on('watererpApp:meterChangeUpdate', function(event, result) {
            $scope.meterChange = result;
        });
        $scope.$on('$destroy', unsubscribe);
        
        
        /*$scope.approvalMeterChange = function(id, remarks){
        	ApplicationTxnService.approveMeterChange(id, remarks);
        	$state.go('meterChange');
        }*/
        
        $scope.getWorkflowHistoryByDomainId = function(requestTypeId) {
        	$scope.requestWorkflowHistorys = [];
            RequestWorkflowHistory.query({page: $scope.page, size: 20, dimainObjectId: $stateParams.id, requestTypeId: requestTypeId}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.requestWorkflowHistorys.push(result[i]);
                }
            });
        };
        if($stateParams.requestTypeId != null){
        	$scope.getWorkflowHistoryByDomainId($stateParams.requestTypeId);
        }
        
        $scope.canDecline = function() {
			var ret = false;
			switch ($scope.meterChange.status) {
			case 0:
				if ($scope.orgRole.id === 15)
					ret = true;
				break;
			case 1:
				if ($scope.orgRole.id === 10)
					ret = true;
				break;
			default:
				break;
			}
			return ret;
		}
        
      //approve a request
		$scope.approve = function(meterChange){
        	return $http.post('/api/meterChanges/meterChangeApprove',
					meterChange).then(
					function(response) {
						console.log("Server response:"
								+ JSON.stringify(response));
						$window.history.back();
					});
        }
		
		// to decline a request
		$scope.declineRequest = function(meterChange){
        	return $http.post('/api/meterChanges/declineRequest',
					meterChange ).then(
					function(response) {
						console.log("Server response:"
								+ JSON.stringify(response));
						//$state.go('meterChange');
						$window.history.back();
					});
        }
    });
