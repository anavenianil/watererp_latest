'use strict';

angular.module('watererpApp').controller('OrgRolesMasterDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'OrgRolesMaster', 'StatusMaster',
        function($scope, $stateParams, $uibModalInstance, entity, OrgRolesMaster, StatusMaster) {

        $scope.orgRolesMaster = entity;
        $scope.statusmasters = StatusMaster.query();
        $scope.load = function(id) {
            OrgRolesMaster.get({id : id}, function(result) {
                $scope.orgRolesMaster = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:orgRolesMasterUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.orgRolesMaster.id != null) {
                OrgRolesMaster.update($scope.orgRolesMaster, onSaveSuccess, onSaveError);
            } else {
                OrgRolesMaster.save($scope.orgRolesMaster, onSaveSuccess, onSaveError);
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
