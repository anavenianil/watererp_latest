'use strict';

angular
		.module('watererpApp')
		.controller(
				'SewerageRequestDetailController',
				function($scope, $rootScope, $stateParams, entity,
						SewerageRequest, TariffCategoryMaster, Receipt, RequestWorkflowHistory, Principal) {
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
        
        $scope.user = Principal.getLogonUser();
        $scope.orgRole = {};
        Principal.getOrgRole().then(function(response) {
			$scope.orgRole = response;
		});
        
        $scope.getWorkflowHistoryByDomainId = function(requestTypeId) {
        	$scope.requestWorkflowHistorys = [];
            RequestWorkflowHistory.query({page: $scope.page, size: 20, dimainObjectId: $stateParams.id, requestTypeId: $stateParams.requestTypeId}, function(result, headers) {
                for (var i = 0; i < result.length; i++) {
                    $scope.requestWorkflowHistorys.push(result[i]);
                }
                $scope.requestAt = $scope.requestWorkflowHistorys[$scope.requestWorkflowHistorys.length-1].assignedTo.login;
                //$scope.requestStatus = $scope.requestWorkflowHistorys[$scope.requestWorkflowHistorys.length-1].statusMaster.id;
                console.log("Request at :"+$scope.requestAt);
                $scope.requestWorkflowStatus = $scope.requestWorkflowHistorys[$scope.requestWorkflowHistorys.length-1].statusMaster.id;
                console.log("status :"+$scope.requestStatus);
            });
        };
        
        $scope.getWorkflowHistoryByDomainId();

        $scope.datePickerForApprovedDate = {};

        $scope.datePickerForApprovedDate.status = {
            opened: false
        };

        $scope.datePickerForApprovedDateOpen = function($event) {
            $scope.datePickerForApprovedDate.status.opened = true;
        };
        
        $scope.datePickerForCompletionDate = {};

        $scope.datePickerForCompletionDate.status = {
            opened: false
        };

        $scope.datePickerForCompletionDateOpen = function($event) {
            $scope.datePickerForCompletionDate.status.opened = true;
        };
        
        var onSaveSuccess = function (result) {
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };
        
        $scope.sewerageApprovalDTO = {};
        $scope.approveSewerageRequest = function () {
            $scope.isSaving = true;
            $scope.sewerageApprovalDTO.sewerageRequest = $scope.sewerageRequest;
            SewerageRequest.sewerageRequestApproval($scope.sewerageApprovalDTO, onSaveSuccess, onSaveError);
        };
        
    });
