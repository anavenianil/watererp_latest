'use strict';

angular.module('watererpApp')
    .controller('ProceedingsDetailController', function ($scope, $rootScope, $state, $stateParams, entity, Proceedings, ApplicationTxn, ApprovalDetails) {
        $scope.proceedings = entity;
        $scope.applicationTxn = {};
        $scope.approvalDetails = {};
        $scope.approvalDetails.applicationTxn = {};
        $scope.approvalDetails.customer = {};
        
        $scope.load = function (id) {
            Proceedings.get({id: id}, function(result) {
                $scope.proceedings = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:proceedingsUpdate', function(event, result) {
            $scope.proceedings = result;
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
        
        $scope.approvalDetailsSave = function(){
        	$('#approveModal').modal('hide');
        	console.log(JSON.stringify($scope.approvalDetails));
            ApprovalDetails.save(($scope.approvalDetails), function(){
            	$state.go('proceedings');
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
