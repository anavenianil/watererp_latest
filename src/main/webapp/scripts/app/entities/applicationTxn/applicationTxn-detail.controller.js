'use strict';

angular.module('watererpApp')
    .controller('ApplicationTxnDetailController', function ($scope, $state, $rootScope, $stateParams, entity, ApplicationTxn, ApplicationTypeMaster, ConnectionTypeMaster, CategoryMaster, PipeSizeMaster, SewerSize, FileNumber, Customer, ApprovalDetails) {
        $scope.applicationTxn = entity;
        $scope.approvalDetails = {};
        $scope.approvalDetails.applicationTxn={};
        $scope.approvalDetails.customer={};
        
        $scope.load = function (id) {
        	$('#viewApplicationTxnModal').modal('hide');
        	$('#approveModal').modal('show');
            ApplicationTxn.get({id: id}, function(result) {
                $scope.applicationTxn = result;
                $scope.approvalDetails.applicationTxn.id = $scope.applicationTxn.id;
                $scope.approvalDetails.customer.id = $scope.applicationTxn.id
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:applicationTxnUpdate', function(event, result) {
            $scope.applicationTxn = result;
        });
        $scope.$on('$destroy', unsubscribe);
        
     
        $scope.approvalDetailsSave = function(){
        	$('#approveModal').modal('hide');
        	console.log(JSON.stringify($scope.approvalDetails));
            ApprovalDetails.save(($scope.approvalDetails), function(){
            	$state.go('applicationTxn.all');
            });
        }
       
        $scope.datePickerForApprovedDate = {};

        $scope.datePickerForApprovedDate.status = {
            opened: false
        };

        $scope.datePickerForApprovedDateOpen = function($event) {
            $scope.datePickerForApprovedDate.status.opened = true;
        };

    });
