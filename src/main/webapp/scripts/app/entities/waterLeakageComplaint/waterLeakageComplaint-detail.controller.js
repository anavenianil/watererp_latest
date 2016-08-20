'use strict';

angular.module('watererpApp')
    .controller('WaterLeakageComplaintDetailController', function ($scope, $rootScope, $stateParams, entity, WaterLeakageComplaint, DivisionMaster, 
    		Principal, StreetMaster, JobCardItemRequirement, RequestWorkflowHistory, $http, MaterialMaster, Uom, BurstComplaint, JobCardSiteStatus) {
        $scope.waterLeakageComplaint = entity;
        
        
        $scope.materialmasters = MaterialMaster.query();
        $scope.uoms = Uom.query();
        
        $scope.jobCardDTO = {};
        
        //$scope.jobCardDTO.jobCardItemRequirements = [];
        $scope.count = 0;
        //var complaintId = 0;
        $scope.burstComplaint = BurstComplaint.getByComplaintId({waterLeakageComplaint : $stateParams.id});
        
        $scope.jobCardSiteStatus = JobCardSiteStatus.getByComplaintId({waterLeakageComplaint : $stateParams.id});
  
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
        
        $scope.getPipe = function(){
        	console.log($scope.jobCardDTO.burstComplaint.pipeType);
        	var pipeName = $scope.jobCardDTO.burstComplaint.pipeType;
        	if(pipeName =='AC' || pipeName == 'STEEL'){
        		$scope.pipeSizes = [{"size": "50"},{"size": "75"},{"size": "100"},{"size": "150"},{"size": "200"},{"size": "225"},{"size": "250"},{"size": "300"},{"size": "375"},{"size": "450"},{"size": "525"},{"size": "575"},{"size": "Other"}];
            }
        	else if(pipeName =='HDPE' || pipeName == 'uPVC'){
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
        
        $scope.orgRole = {};
		Principal.getOrgRole().then(function(response) {
			$scope.orgRole = response;
		});
        
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
		$scope.jobCardDTO.waterLeakageComplaint = entity;
		$scope.forwardRequest = function(){
			$scope.jobCardDTO.domainId = $stateParams.id;
			
			console.log("approve");
        	return $http.post('/api/waterLeakageComplaints/forwardRequest',
        			$scope.jobCardDTO).then(
					function(response) {
						console.log("Server response:"
								+ JSON.stringify(response));
					});
        }
		
		
		$scope.approveRequest = function(){
			$scope.jobCardDTO.domainId = $stateParams.id;
			return $http.post('/api/waterLeakageComplaints/forwardRequest',
        			$scope.jobCardDTO).then(
					function(response) {
						console.log("Server response:"
								+ JSON.stringify(response));
					});
		}
		
		//create array for items
		$scope.jobCardDTO.jobCardItemRequirements = [];
        $scope.createItemArr = function(){
       		$scope.jobCardDTO.jobCardItemRequirements[$scope.count]= {};
       		$scope.count = $scope.count +1;
        }
        
        //remove
        $scope.removeItemArr = function(indexId) {
            $scope.count = $scope.count -1;
            $scope.jobCardDTO.jobCardItemRequirements.splice(indexId, 1);
          };
		
         
    });
