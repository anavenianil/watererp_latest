'use strict';

angular.module('watererpApp').controller('ReqOrgWorkflowMappingDialogController',
        function($scope, $stateParams, ReqOrgWorkflowMapping, WorkflowMaster, RequestMaster, OrgRoleInstance, StatusMaster, $http, ParseLinks, $state) {

        $scope.reqOrgWorkflowMapping = {};
        $scope.workflowmasters = WorkflowMaster.query();
        $scope.requestmasters = RequestMaster.query();
        //$scope.statusmasters = StatusMaster.query();
        //$scope.orgroleinstances = OrgRoleInstance.query();
        $scope.getOrgRoleInstance = function() {
        	$scope.orgroleinstances = [];
			return $http.get('/api/orgRoleInstances/getAll'
					).then(
					function(response) {
						$scope.orgroleinstances = response.data;
					});
		}
        $scope.getOrgRoleInstance();
        
        
        $scope.getStatusMaster = function() {
        	$scope.statusmasters = [];
            StatusMaster.query({page: $scope.page, size: 20, description1:'GENERAL'}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.statusmasters.push(result[i]);
                }
            });
        };
        $scope.getStatusMaster();
        
        $scope.load = function(id) {
            ReqOrgWorkflowMapping.get({id : id}, function(result) {
                $scope.reqOrgWorkflowMapping = result;
            });
        };
        
        if($stateParams.id != null){
        	$scope.load($stateParams.id);
        }

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:reqOrgWorkflowMappingUpdate', result);
            //$uibModalInstance.close(result);
            $scope.isSaving = false;
            $state.go('reqOrgWorkflowMapping');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.reqOrgWorkflowMapping.id != null) {
                ReqOrgWorkflowMapping.update($scope.reqOrgWorkflowMapping, onSaveSuccess, onSaveError);
            } else {
                ReqOrgWorkflowMapping.save($scope.reqOrgWorkflowMapping, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            //$uibModalInstance.dismiss('cancel');
        	$state.go('reqOrgWorkflowMapping');
        };
        $scope.datePickerForCreationDate = {};

        $scope.datePickerForCreationDate.status = {
            opened: false
        };

        $scope.datePickerForCreationDateOpen = function($event) {
            $scope.datePickerForCreationDate.status.opened = true;
        };
        $scope.datePickerForLastModifiedDate = {};

        $scope.datePickerForLastModifiedDate.status = {
            opened: false
        };

        $scope.datePickerForLastModifiedDateOpen = function($event) {
            $scope.datePickerForLastModifiedDate.status.opened = true;
        };
});
