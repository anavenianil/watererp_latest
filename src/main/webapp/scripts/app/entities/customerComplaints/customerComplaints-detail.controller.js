'use strict';

angular.module('watererpApp')
    .controller('CustomerComplaintsDetailController', function ($scope, $rootScope, $stateParams, entity, CustomerComplaints, ComplaintTypeMaster,
    		RequestWorkflowHistory, ParseLinks,ApplicationTxnService) {
        $scope.customerComplaints = entity;
        $scope.load = function (id) {
            CustomerComplaints.get({id: id}, function(result) {
                $scope.customerComplaints = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:customerComplaintsUpdate', function(event, result) {
            $scope.customerComplaints = result;
        });
        $scope.$on('$destroy', unsubscribe);
        
        $scope.getWorkflowHistoryByDomainId = function() {
        	$scope.requestWorkflowHistorys = [];
            RequestWorkflowHistory.query({page: $scope.page, size: 20, dimainObjectId: $stateParams.id}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.requestWorkflowHistorys.push(result[i]);
                }
            });
        };
        if($stateParams.id != null){
        	$scope.getWorkflowHistoryByDomainId();
        }
        
        $scope.approve = function(id){
        	ApplicationTxnService.approveCustComplaint(id);
        }

    });
