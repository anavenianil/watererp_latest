'use strict';

angular.module('watererpApp').controller('EmpRoleMappingDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'EmpRoleMapping', 'User', 'OrgRoleInstance', 'StatusMaster', '$http', 'ParseLinks',
        function($scope, $stateParams, $uibModalInstance, entity, EmpRoleMapping, User, OrgRoleInstance, StatusMaster, $http, ParseLinks) {

        $scope.empRoleMapping = entity;
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

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:empRoleMappingUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
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
            $uibModalInstance.dismiss('cancel');
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
}]);
