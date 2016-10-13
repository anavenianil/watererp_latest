'use strict';

angular.module('watererpApp')
    .controller('MeterChangeDetailController', function ($scope, $rootScope, $stateParams, MeterChange, CustDetails, MeterDetails, User,
    		ApplicationTxnService, RequestWorkflowHistory, ParseLinks, $state, Principal, $http, $window) {
        $scope.meterChange = {};
        
        $scope.users = User.query();
        $scope.user = Principal.getLogonUser();
        
        $scope.maxDt= new Date(); 

        $scope.orgRole = {};
		Principal.getOrgRole().then(function(response) {
			$scope.orgRole = response;
		});
		
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
            $scope.meterChange.id = result.id;
            $scope.isSaving = false;
            $('#saveSuccessfullyModal').modal('show');
         
           //$state.go('meterChange');
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
                $scope.requestAt = $scope.requestWorkflowHistorys[$scope.requestWorkflowHistorys.length-1].assignedTo.login;
                $scope.requestStatus = $scope.requestWorkflowHistorys[$scope.requestWorkflowHistorys.length-1].statusMaster.id;
                console.log("Request at :"+$scope.requestAt);
                $scope.requestStatus = $scope.requestWorkflowHistorys[$scope.requestWorkflowHistorys.length-1].statusMaster.id;
                console.log("status :"+$scope.requestStatus);
            });
        };
        if($stateParams.requestTypeId != null){
        	$scope.getWorkflowHistoryByDomainId($stateParams.requestTypeId);
        }
        
        $scope.canDecline = function() {
			var ret = false;
			switch ($scope.meterChange.status) {
			case'INITIATED':
				if ($scope.orgRole.id === 16)
					ret = true;
				break;
			case 'PROCESSING':
				if ($scope.orgRole.id === 9)
					ret = true;
				break;
			default:
				break;
			}
			return ret;
		}
        
        $scope.clear=function()
        {
        	$('#saveSuccessfullyModal').modal('hide');
        	$state.go('meterChange');
        	
        }
      //approve a request
		$scope.approve = function(meterChange){
			console.log("approve");
        	return $http.post('/api/meterChanges/meterChangeApprove',
					meterChange).then(
					function(response) {
						console.log("Server response:"
								+ JSON.stringify(response));
						 $('#saveSuccessfullyModal').modal('show');
						//$state.go('meterChange');
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
						$state.go('meterChange');
					});
        }
    });
