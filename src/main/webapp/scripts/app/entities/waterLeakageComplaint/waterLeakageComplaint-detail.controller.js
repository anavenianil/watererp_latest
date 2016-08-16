'use strict';

angular.module('watererpApp')
    .controller('WaterLeakageComplaintDetailController', function ($scope, $rootScope, $stateParams, entity, WaterLeakageComplaint, DivisionMaster, 
    		StreetMaster, JobCardItemRequirement, RequestWorkflowHistory, $http) {
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
        
        
        $scope.pipeTypes = [{"id":"1", "pipeName" : "AC"},
                            {"id":"2", "pipeName" : "STEEL"},
                            {"id":"3", "pipeName" : "HDPE"},
                            {"id":"4", "pipeName" : "uPVC"}];
        
        $scope.getPipe = function(pipeTypeId){
        	if(pipeTypeId ==1 || pipeTypeId == 2){
        		$scope.pipeSizes = [{"size": "50"},{"size": "75"},{"size": "100"},{"size": "150"},{"size": "200"},{"size": "225"},{"size": "250"},{"size": "300"},{"size": "375"},{"size": "450"},{"size": "525"},{"size": "575"},{"size": "Other"}];
            }
        	else{
        		$scope.pipeSizes = [{"size": "63"},{"size": "75"},{"size": "90"},{"size": "110"},{"size": "140"},{"size": "160"},{"size": "200"},{"size": "250"},{"size": "315"},{"size": "350"},{"size": "400"},{"size": "Other"}];
        	}
        }
        
        $scope.assignPipe = function(size){
        	if(size == "Other"){
        		$scope.jobCardDTO.burstComplaint.pipeSize = "";
        	}
        	else{
        		$scope.jobCardDTO.burstComplaint.pipeSize = size;
        	}
        }
        
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

        
        $scope.datePickerForApprovedDate = {};

		$scope.datePickerForApprovedDate.status = {
			opened : false
		};

		$scope.datePickerForApprovedDateOpen = function($event) {
			$scope.datePickerForApprovedDate.status.opened = true;
		};
		
		
		//approve a request
		$scope.jobCardDTO = {};
		$scope.approveRequest = function(jobCardDTO){
			$scope.jobCardDTO = jobCardDTO;
			$scope.jobCardDTO.domainId = $stateParams.id
			console.log("approve");
        	return $http.post('/api/waterLeakageComplaints/forwardRequest',
        			$scope.jobCardDTO).then(
					function(response) {
						console.log("Server response:"
								+ JSON.stringify(response));
					});
        }
    });
