'use strict';

angular.module('watererpApp').controller('EmpRoleMappingDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'EmpRoleMapping', 'User', 'OrgRoleInstance', 'StatusMaster',
        function($scope, $stateParams, $uibModalInstance, entity, EmpRoleMapping, User, OrgRoleInstance, StatusMaster) {

        $scope.empRoleMapping = entity;
        $scope.users = User.query();
        $scope.orgroleinstances = OrgRoleInstance.query();
        $scope.statusmasters = StatusMaster.query();
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
