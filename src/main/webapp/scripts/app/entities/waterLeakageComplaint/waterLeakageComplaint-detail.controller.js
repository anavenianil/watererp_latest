'use strict';

angular.module('watererpApp')
    .controller('WaterLeakageComplaintDetailController', function ($scope, $rootScope, $stateParams, entity, WaterLeakageComplaint, DivisionMaster, 
    		Principal, StreetMaster, JobCardItemRequirement, RequestWorkflowHistory, $http, MaterialMaster, Uom, BurstComplaint, JobCardSiteStatus, ValveComplaint, 
    		HydrantComplaint, $state) {
        $scope.waterLeakageComplaint = entity;
        
        
        $scope.materialmasters = MaterialMaster.query();
        $scope.uoms = Uom.query();
        
        $scope.user = Principal.getLogonUser();
        console.log("login user:"+$scope.user.login);
        //$scope. loginUser = $scope.user.login;
        
        $scope.jobCardDTO = {};
        
        $scope.count = 0;
        $scope.valveCount = 0;
        $scope.jobCardDTO.itemRequired = false;
        
        $scope.burstComplaint = BurstComplaint.getByComplaintId({waterLeakageComplaint : $stateParams.id});
        $scope.valveComplaints = ValveComplaint.getByComplaintId({waterLeakageComplaint : $stateParams.id});
        $scope.hydrantComplaint = HydrantComplaint.getByComplaintId({waterLeakageComplaint : $stateParams.id});
        
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
                //console.log("Request WorkFlow History Length :"+$scope.requestWorkflowHistorys.length);
                //console.log("Last request is at :"+$scope.requestWorkflowHistorys[$scope.requestWorkflowHistorys.length-1].assignedTo.login);
                $scope.requestAt = $scope.requestWorkflowHistorys[$scope.requestWorkflowHistorys.length-1].assignedTo.login;
                $scope.requestStatus = $scope.requestWorkflowHistorys[$scope.requestWorkflowHistorys.length-1].statusMaster.id;
                console.log("Request at :"+$scope.requestAt);
                $scope.requestStatus = $scope.requestWorkflowHistorys[$scope.requestWorkflowHistorys.length-1].statusMaster.id;
                console.log("status :"+$scope.requestStatus);
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
						$state.go('waterLeakageComplaint');
						/*console.log("Server response:"
								+ JSON.stringify(response));*/
					});
        }
		
		// to decline a request
		$scope.declineRequest = function(){
        	return $http.post('/api/waterLeakageComplaints/declineRequest',
					$scope.jobCardDTO ).then(
					function(response) {
						$state.go('waterLeakageComplaint');
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
          
          //create array for valveComplaint
          $scope.jobCardDTO.valveComplaints = [];
          $scope.createValveArr = function(){
         		$scope.jobCardDTO.valveComplaints[$scope.valveCount]= {};
         		$scope.valveCount = $scope.valveCount +1;
          }
          
          //remove array for valveComplaint
          $scope.removeValveArr = function(indexId) {
              $scope.valveCount = $scope.valveCount -1;
              $scope.jobCardDTO.valveComplaints.splice(indexId, 1);
            };
            
            
            
            $scope.itemLength = function(){
            	if($scope.jobCardDTO.itemRequired === false){
            		$scope.jobCardDTO.jobCardItemRequirements.length = 0;
            		$scope.count = 0;
            	}
            	
            }
            
            $scope.datePickerForClosedTime = {};

            $scope.datePickerForClosedTime.status = {
                opened: false
            };

            $scope.datePickerForClosedTimeOpen = function($event) {
                $scope.datePickerForClosedTime.status.opened = true;
            };
            $scope.datePickerForOpenTime = {};

            $scope.datePickerForOpenTime.status = {
                opened: false
            };

            $scope.datePickerForOpenTimeOpen = function($event) {
                $scope.datePickerForOpenTime.status.opened = true;
            };
    });
