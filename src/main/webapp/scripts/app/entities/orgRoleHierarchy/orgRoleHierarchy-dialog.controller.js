'use strict';

angular.module('watererpApp').controller('OrgRoleHierarchyDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'OrgRoleHierarchy', 'StatusMaster',
        function($scope, $stateParams, $uibModalInstance, entity, OrgRoleHierarchy, StatusMaster) {

        $scope.orgRoleHierarchy = entity;
        $scope.statusmasters = StatusMaster.query();
        $scope.load = function(id) {
            OrgRoleHierarchy.get({id : id}, function(result) {
                $scope.orgRoleHierarchy = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:orgRoleHierarchyUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.orgRoleHierarchy.id != null) {
                OrgRoleHierarchy.update($scope.orgRoleHierarchy, onSaveSuccess, onSaveError);
            } else {
                OrgRoleHierarchy.save($scope.orgRoleHierarchy, onSaveSuccess, onSaveError);
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
