'use strict';

angular.module('watererpApp').controller('EmpRoleMappingDialogController',
    /*['$scope', '$stateParams', '$uibModalInstance', 'entity', 'EmpRoleMapping', 'User', 'OrgRoleInstance', 'StatusMaster', '$http', 'ParseLinks',*/
        function($scope, $stateParams, /*$uibModalInstance, entity,*/ EmpRoleMapping, User, OrgRoleInstance, StatusMaster, $http, ParseLinks, $state) {

        $scope.empRoleMapping = {};
        //$scope.users = User.query();
        //$scope.orgroleinstances = OrgRoleInstance.query();
        //$scope.statusmasters = StatusMaster.query();
        
        $scope.getOrgRoleInstance = function() {
        	$scope.orgroleinstances = [];
			return $http.get('/api/orgRoleInstances/getAll').then(function(response) {
						$scope.orgroleinstances = response.data;
					});
		}
        $scope.getOrgRoleInstance();
        
        $scope.getUser = function() {
        	$scope.users = [];
			return $http.get('/api/users/getAll'
					).then(
					function(response) {
						$scope.users = response.data;
					});
		}
        $scope.getUser();
        
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
            EmpRoleMapping.get({id : id}, function(result) {
                $scope.empRoleMapping = result;
            });
        };
        
        if($stateParams.id !=null){
        	$scope.load($stateParams.id);
        }

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:empRoleMappingUpdate', result);
            //$uibModalInstance.close(result);
            $scope.isSaving = false;
            $('#saveSuccessfullyModal').modal('hide');
            $state.go('empRoleMapping');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.empRoleMapping.id != null) {
                EmpRoleMapping.update($scope.empRoleMapping, onSaveSuccess, onSaveError);
            } else {
                EmpRoleMapping.save($scope.empRoleMapping, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            //$uibModalInstance.dismiss('cancel');
        	$scope.empRoleMapping = {};
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
        
        $scope.getByUser = function(userId){
        	if(userId !=null){
        		$scope.empRoleMapping = EmpRoleMapping.getByUserId({userId : userId});
        	}
        	
        	if($scope.empRoleMapping.id == null){
        		$scope.empRoleMapping.user = {};
        		$scope.empRoleMapping.user.id = userId;
        	}
        	//console.log($scope.empMaster);
        }
        $scope.empRoles = {};
        $scope.empRoles.orRoleInstance = {};
        $scope.empRoles.user = {};
        $scope.getByOrgId = function(orgRoleInstanceId){
        	$('#saveSuccessfullyModal').modal('show');
        	EmpRoleMapping.getMappingsByOrgRoleInstance({orgRoleInstanceId:orgRoleInstanceId}, onRetrievalEmpRole);
        }
        
        var onRetrievalEmpRole = function(result){
        	$scope.message = "Role "+ result.orgRoleInstance.orgRoleName+ " is already assigned to "+result.user.firstName +" "+result.user.lastName+".";
        }
        
        
}/*]*/);
