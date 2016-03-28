'use strict';

angular.module('watererpApp')
    .controller('ApplicationTxnDetailController', function ($state, $scope, $rootScope, $stateParams, entity, ApplicationTxn, 
    		ApplicationTxnService, RequestWorkflowHistory, ParseLinks) {
        $scope.applicationTxn = entity;
        
        $scope.load = function (id) {
            ApplicationTxn.get({id: id}, function(result) {
                $scope.applicationTxn = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:applicationTxnUpdate', function(event, result) {
            $scope.applicationTxn = result;
        });
        $scope.$on('$destroy', unsubscribe);
        
        $scope.status = null;
        $scope.getApplicationTxn = function (id, status) {
        	$('#approveModal').modal('show');
            ApplicationTxn.get({id: id}, function(result) {
                $scope.applicationTxn = result;
            });
        };
        
        /*RequestWorkflowHistoryfindByDomainObject(id)
		.then(function(data) {
			$scope.workflowHistorys = data;
		});*/
        $scope.getWorkflowHistoryByDomainId = function() {
        	$scope.requestWorkflowHistorys = [];
            RequestWorkflowHistory.query({page: $scope.page, size: 20, dimainObjectId: $stateParams.id}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.requestWorkflowHistorys.push(result[i]);
                }
            });
        };
        $scope.getWorkflowHistoryByDomainId();
        
        $scope.approvalDetailsSave = function(id, remarks){
        	$('#approveModal').modal('hide');
        	//console.log(JSON.stringify($scope.approvalDetails));
        	ApplicationTxnService.approveRequest(id, remarks);
        	$state.go('request');
        }
        

        $scope.datePickerForApprovedDate = {};

        $scope.datePickerForApprovedDate.status = {
            opened: false
        };

        $scope.datePickerForApprovedDateOpen = function($event) {
            $scope.datePickerForApprovedDate.status.opened = true;
        };

    });
