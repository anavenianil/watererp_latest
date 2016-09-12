'use strict';

angular.module('watererpApp').controller('OrgRoleInstanceDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'OrgRoleInstance', 'StatusMaster', 'OrgRoleHierarchy', '$http', 'ParseLinks',
        function($scope, $stateParams, $uibModalInstance, entity, OrgRoleInstance, StatusMaster, OrgRoleHierarchy, $http, ParseLinks) {

        $scope.orgRoleInstance = entity;
        //$scope.statusmasters = StatusMaster.query();
        //$scope.orgrolehierarchys = OrgRoleHierarchy.query();
        //$scope.departmentsmasters = DepartmentsMaster.query();
        $scope.getOrgRoleInstance = function() {
        	$scope.orgroleinstances = [];
			return $http.get('/api/orgRoleInstances/getAll'
					).then(
					function(response) {
						$scope.orgroleinstances = response.data;
					});
		}
        $scope.getOrgRoleInstance();
        
        
        $scope.getOrgrolehierarchys = function() {
        	$scope.orgrolehierarchys = [];
			return $http.get('/api/orgRoleHierarchys/getAll').then(function(response) {
						$scope.orgrolehierarchys = response.data;
					});
		}
        $scope.getOrgrolehierarchys();
        
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
            OrgRoleInstance.get({id : id}, function(result) {
                $scope.orgRoleInstance = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:orgRoleInstanceUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.orgRoleInstance.id != null) {
                OrgRoleInstance.update($scope.orgRoleInstance, onSaveSuccess, onSaveError);
            } else {
                OrgRoleInstance.save($scope.orgRoleInstance, onSaveSuccess, onSaveError);
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
