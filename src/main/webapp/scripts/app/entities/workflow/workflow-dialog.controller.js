'use strict';

angular.module('watererpApp').controller('WorkflowDialogController',
        function($scope, $state, $stateParams, Workflow, WorkflowMaster, WorkflowRelations, OrgRoleInstance, WorkflowRelationships, 
        		WorkflowStageMaster, ParseLinks, $http) {

        $scope.workflow = {};
        $scope.workflowmasters = WorkflowMaster.query();
        $scope.workflowrelationss = WorkflowRelations.query();
        //$scope.orgroleinstances = OrgRoleInstance.query();
        $scope.workflowrelationshipss = WorkflowRelationships.query();
        $scope.workflowstagemasters = WorkflowStageMaster.query();
        $scope.workflowMaster = {};
        $scope.workflowDTO = {};
        $scope.workflowDTO.workflowMaster = {};
        $scope.count = 0;
        
        $scope.load = function(id) {
            Workflow.get({id : id}, function(result) {
                $scope.workflow = result;
            });
        };
        
        if($stateParams.id != null){
        	$scope.load($stateParams.id);
        }

        $scope.getWorkflow = function(workflowMasterId) {
            Workflow.query({page: $scope.page, size: 20, workflowMasterId:workflowMasterId, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.workflows.push(result[i]);
                }
            });
        };
        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:workflowUpdate', result);
            $scope.workflowMaster = result;
            //$uibModalInstance.close(result);
            $scope.isSaving = false;
            $state.go('workflow');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save1 = function () {
            $scope.isSaving = true;
            
            for(var i=0; i<$scope.workflows.length; i++){
            	$scope.workflows[i].workflowMaster = $scope.workflowMaster;
            	if ($scope.workflows[i].id != null) {
            		//Workflow.update($scope.workflows[i], onSaveSuccess, onSaveError);
                    //Workflow.update($scope.workflow, onSaveSuccess, onSaveError);
                } else {
                	//Workflow.save($scope.workflows[i],onSaveSuccess, onSaveError);
                    //Workflow.save($scope.workflow, onSaveSuccess, onSaveError);
                }
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        
        //save
        $scope.save = function(){
			$scope.isSaving = true;
			$scope.workflowDTO.workflows = $scope.workflows;
			if($scope.workflowDTO.workflowName == null){
				$scope.workflowDTO.workflowMaster = $scope.workflow.workflowMaster;
			}
        	return $http.post('/api/workflows/create',
        			$scope.workflowDTO).then(
					function(response) {
						$scope.isSaving = false;
						/*console.log("Server response:"
								+ JSON.stringify(response));*/
					});
        }
        
        $scope.getOrgRoleInstance = function() {
        	$scope.orgroleinstances = [];
			return $http.get('/api/orgRoleInstances/getAll'
					).then(
					function(response) {
						$scope.orgroleinstances = response.data;
					});
		}
        
        $scope.getOrgRoleInstance();
        
        
        $scope.getWorkflow = function(workflowMasterId){
        	$scope.workflows = [];
        	if(workflowMasterId == 2){
        	//	$scope.workflowDTO.workflows.workflowMaster.workflowName = "";
        		$scope.count = 0;
        	}
        	else {
        		Workflow.query({page: $scope.page, size: 20, workflowMasterId: workflowMasterId}, function(result, headers) {
                    $scope.links = ParseLinks.parse(headers('link'));
                    for (var i = 0; i < result.length; i++) {
                        $scope.workflows.push(result[i]);
                    }
                    $scope.count = $scope.workflows.length;
                });
        	}
        }
        
        
        $scope.assign = function(){
        	$scope.workflows[$scope.count-1].stageId = $scope.workflows[$scope.count-2].stageId +1;
       		$scope.workflows[$scope.count-1].absoluteFromRole = $scope.workflows[$scope.count-2].absoluteToRole; 
       		$scope.workflows[$scope.count-1].relativeFromRole = $scope.workflows[$scope.count-2].relativeToRole;
        }
        
        $scope.changeExistingAbsolute = function(indexId , toRoleId ){
        	if($scope.workflows.length-1 !=indexId){
        		$scope.workflows[indexId + 1].absoluteFromRole.id = toRoleId;
        	}
        }
        
        $scope.changeExistingRelative = function(indexId , toRoleId ){
        	if($scope.workflows.length-1 !=indexId){
        		$scope.workflows[indexId + 1].relativeFromRole.id = toRoleId;
        	}
        }
        
      //create array for items
        $scope.createItemArr = function(){
       		$scope.workflows[$scope.count]= {};
       		$scope.count = $scope.count +1;
       		if($scope.count == 1){
       			$scope.workflows[$scope.count-1].stageId = 1;
       		}
       		else{
       			$scope.assign();
       		}
        }
        
      //for removing items
        $scope.removeItemArr = function(indexId) {
        	$scope.workflows[$scope.count].splice = {};
            $scope.count = $scope.count -1;
            $scope.workflows[$scope.count].splice(indexId, 1);
          };
});
