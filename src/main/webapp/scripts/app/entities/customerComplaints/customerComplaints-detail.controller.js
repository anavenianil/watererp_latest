'use strict';

angular.module('watererpApp')
    .controller('CustomerComplaintsDetailController', function ($scope, $state, $rootScope, $stateParams, entity, CustomerComplaints, ApplicationTxn, ComplaintTypeMaster, 
    		ApplicationTxnService, RequestWorkflowHistory, ParseLinks) {
        $scope.customerComplaints = entity;
        $scope.approvalDetails = {};
        
        $scope.load = function (id) {
            CustomerComplaints.get({id: id}, function(result) {
                $scope.customerComplaints = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:customerComplaintsUpdate', function(event, result) {
            $scope.customerComplaints = result;
        });
        $scope.$on('$destroy', unsubscribe);
        
        $scope.getCustomerComplaints = function (id) {
        	$scope.approvalDetails.approvedDate = new Date();
        	$('#approveModal').modal('show');
            $scope.load(id);
        };
        
        $scope.approvalDetailsSave = function(id, remarks){
        	$('#approveModal').modal('hide');
        	//console.log(JSON.stringify($scope.approvalDetails));
        	ApplicationTxnService.approveCustomerComplaints(id, remarks);
        	$state.go('customerComplaints');
        }
        
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

    });
