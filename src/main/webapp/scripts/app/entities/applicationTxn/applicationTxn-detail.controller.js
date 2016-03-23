'use strict';

angular.module('watererpApp')
    .controller('ApplicationTxnDetailController', function ($scope, $state, $rootScope, $stateParams, entity, ApplicationTxn, 
    		ApplicationTypeMaster, ConnectionTypeMaster, CategoryMaster, PipeSizeMaster, SewerSize, FileNumber, Customer, ApprovalDetails,
    		ApplicationTxnService) {
        $scope.applicationTxn = entity;
        $scope.approvalDetails = {};
        $scope.approvalDetails.applicationTxn={};
        $scope.approvalDetails.customer={};
        
        $scope.status = null;
        $scope.load = function (id, status) {
        	$('#approveModal').modal('show');
            ApplicationTxn.get({id: id}, function(result) {
                $scope.applicationTxn = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:applicationTxnUpdate', function(event, result) {
            $scope.applicationTxn = result;
        });
        $scope.$on('$destroy', unsubscribe);
        
     
        $scope.approvalDetailsSave = function(id, remarks){
        	$('#approveModal').modal('hide');
        	//console.log(JSON.stringify($scope.approvalDetails));
        	ApplicationTxnService.approveRequest(id, remarks);
        	$state.go('applicationTxn');
        }
        
     // to Approve a request
		/*$scope.approveRequest = function(id) {
			RequisitionService.approveRequest(id).then(
					function(data) {
						$scope.requisitions = data;
						$state.go('home');
					});
		};*/
       
        $scope.datePickerForApprovedDate = {};

        $scope.datePickerForApprovedDate.status = {
            opened: false
        };

        $scope.datePickerForApprovedDateOpen = function($event) {
            $scope.datePickerForApprovedDate.status.opened = true;
        };

    });