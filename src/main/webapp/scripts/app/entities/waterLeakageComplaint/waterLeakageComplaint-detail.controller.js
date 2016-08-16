'use strict';

angular.module('watererpApp')
    .controller('WaterLeakageComplaintDetailController', function ($scope, $rootScope, $stateParams, entity, WaterLeakageComplaint, DivisionMaster, 
    		StreetMaster, JobCardItemRequirement, RequestWorkflowHistory) {
        $scope.waterLeakageComplaint = entity;
        $scope.load = function (id) {
            WaterLeakageComplaint.get({id: id}, function(result) {
                $scope.waterLeakageComplaint = result;
            });
        };
        var unsubscribe = $rootScope.$on('watererpApp:waterLeakageComplaintUpdate', function(event, result) {
            $scope.waterLeakageComplaint = result;
        });
        $scope.$on('$destroy', unsubscribe);
        
        
        $scope.getWorkflowHistoryByDomainId = function(requestTypeId) {
        	$scope.requestWorkflowHistorys = [];
            RequestWorkflowHistory.query({page: $scope.page, size: 20, dimainObjectId: $stateParams.id, requestTypeId: requestTypeId}, function(result, headers) {
                //$scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.requestWorkflowHistorys.push(result[i]);
                }
            });
        };
        if($stateParams.requestTypeId != null){
        	$scope.getWorkflowHistoryByDomainId($stateParams.requestTypeId);
        }

    });
