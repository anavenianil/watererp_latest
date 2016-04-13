'use strict';

angular.module('watererpApp').controller('OrgRoleInstanceDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'OrgRoleInstance', 'StatusMaster', 'OrgRoleHierarchy',
        function($scope, $stateParams, $uibModalInstance, entity, OrgRoleInstance, StatusMaster, OrgRoleHierarchy) {

        $scope.orgRoleInstance = entity;
        $scope.statusmasters = StatusMaster.query();
        $scope.orgrolehierarchys = OrgRoleHierarchy.query();
        //$scope.departmentsmasters = DepartmentsMaster.query();
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
