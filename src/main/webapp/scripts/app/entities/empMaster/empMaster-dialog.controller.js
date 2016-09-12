'use strict';

angular.module('watererpApp').controller('EmpMasterDialogController',
    /*['$scope', '$stateParams', '$uibModalInstance', 'entity', 'EmpMaster', 'User', 'OrgRoleInstance', 'DesignationMaster', 'StatusMaster',*/
        function($scope, $stateParams, /*$uibModalInstance, entity,*/ EmpMaster, User, OrgRoleInstance, DesignationMaster, StatusMaster, 
        		$http, ParseLinks, $state	) {

        $scope.empMaster = {};
        //$scope.users = User.query();
        //$scope.orgroleinstances = OrgRoleInstance.query();
        $scope.designationmasters = DesignationMaster.query();
        //$scope.statusmasters = StatusMaster.query();
        $scope.employeestatuss= [{"value":"MARRIED"},{"value":"UNMARRIED"}];
        $scope.getOrgRoleInstance = function() {
        	$scope.orgroleinstances = [];
			return $http.get('/api/orgRoleInstances/getAll').then(function(response) {
						$scope.orgroleinstances = response.data;
					});
		}
        $scope.getOrgRoleInstance();
        
        
        $scope.getDesignationMaster = function() {
        	$scope.designationmasters = [];
			return $http.get('/api/designationMasters/getAll').then(function(response) {
						$scope.designationmasters = response.data;
					});
		}
        $scope.getDesignationMaster();
        
        $scope.getStatusMaster = function() {
        	$scope.statusmasters = [];
            StatusMaster.query({page: $scope.page, size: 20, description1:'GENERAL' , description2:'EMPLOYEE STATUS'}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.statusmasters.push(result[i]);
                }
            });
        };
        $scope.getStatusMaster();
        
        
        $scope.getUser = function() {
        	$scope.users = [];
			return $http.get('/api/users/getAll'
					).then(
					function(response) {
						$scope.users = response.data;
					});
		}
        $scope.getUser();
        
        $scope.load = function(id) {
            EmpMaster.get({id : id}, function(result) {
                $scope.empMaster = result;
            });
        };
        
        if($stateParams.id != null){
        	$scope.load($stateParams.id);
        }
        $scope.dtMax = new Date();

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:empMasterUpdate', result);
            //$uibModalInstance.close(result);
            $scope.isSaving = false;
            $state.go('empMaster');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.empMaster.id != null) {
                EmpMaster.update($scope.empMaster, onSaveSuccess, onSaveError);
            } else {
                EmpMaster.save($scope.empMaster, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            //$uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForDateOfBirth = {};

        $scope.datePickerForDateOfBirth.status = {
            opened: false
        };

        $scope.datePickerForDateOfBirthOpen = function($event) {
            $scope.datePickerForDateOfBirth.status.opened = true;
        };
        $scope.datePickerForJoiningDate = {};

        $scope.datePickerForJoiningDate.status = {
            opened: false
        };

        $scope.datePickerForJoiningDateOpen = function($event) {
            $scope.datePickerForJoiningDate.status.opened = true;
        };
        
        $scope.getByUser = function(userId){
        	$scope.empMaster = EmpMaster.getByUserId({userId : userId});
        	if($scope.empMaster.id == null){
        		$scope.empMaster.user = {};
        		$scope.empMaster.user.id = userId;
        	}
        	//console.log($scope.empMaster);
        }
}/*]*/);
