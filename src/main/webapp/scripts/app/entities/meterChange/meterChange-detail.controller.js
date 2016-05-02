'use strict';

angular.module('watererpApp')
    .controller('MeterChangeDetailController', function ($scope, $rootScope, $stateParams, entity, MeterChange, CustDetails, MeterDetails, User,
    		ApplicationTxnService, RequestWorkflowHistory, ParseLinks, $state) {
        $scope.meterChange = entity;
        $scope.meterChange.remarks = "";
        $scope.load = function (id) {
            MeterChange.get({id: id}, function(result) {
                $scope.meterChange = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:meterChangeUpdate', function(event, result) {
            $scope.meterChange = result;
        });
        $scope.$on('$destroy', unsubscribe);
        
        
        $scope.approvalMeterChange = function(id, remarks){
        	ApplicationTxnService.approveMeterChange(id, remarks);
        	$state.go('meterChange');
        }
        
        $scope.getWorkflowHistoryByDomainId = function() {
        	$scope.requestWorkflowHistorys = [];
            RequestWorkflowHistory.query({page: $scope.page, size: 20, dimainObjectId: $stateParams.id, requestId: 7}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.requestWorkflowHistorys.push(result[i]);
                }
            });
        };
        if($stateParams.id != null){
        	$scope.getWorkflowHistoryByDomainId($stateParams.id);
        }
    });
