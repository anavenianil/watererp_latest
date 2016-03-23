'use strict';

angular.module('watererpApp')
    .controller('FeasibilityStudyDetailController', function ($scope, $state, $rootScope, $stateParams, entity, FeasibilityStudy, SchemeMaster, 
    			TariffCategoryMaster, MakeOfPipe, MainWaterSize, MainSewerageSize, DocketCode, ApplicationTxn, CategoryMaster, SewerSize, 
    			PipeSizeMaster, FeasibilityStatus, ApplicationTxnService) {
        $scope.feasibilityStudy = entity;
        $scope.approvalDetails = {};
        $scope.approvalDetails.applicationTxn = {};
        $scope.approvalDetails.customer = {};
        
        $scope.load = function (id) {
            FeasibilityStudy.get({id: id}, function(result) {
                $scope.feasibilityStudy = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:feasibilityStudyUpdate', function(event, result) {
            $scope.feasibilityStudy = result;
        });
        $scope.$on('$destroy', unsubscribe);
        
        $scope.getApplicationTxn = function (id) {
        	console.log(id);
        	$('#approveModal').modal('show');
            ApplicationTxn.get({id: id}, function(result) {
                $scope.applicationTxn = result;
                $scope.approvalDetails.applicationTxn.id = $scope.applicationTxn.id;
                $scope.approvalDetails.customer.id = $scope.applicationTxn.id
            });
        };
        
        $scope.approvalDetailsSave = function(id, remarks){
        	$('#approveModal').modal('hide');
        	//console.log(JSON.stringify($scope.approvalDetails));
        	ApplicationTxnService.approveRequest(id, remarks);
        	$state.go('feasibilityStudy');
        }
       
        $scope.datePickerForApprovedDate = {};

        $scope.datePickerForApprovedDate.status = {
            opened: false
        };

        $scope.datePickerForApprovedDateOpen = function($event) {
            $scope.datePickerForApprovedDate.status.opened = true;
        };

    });
