'use strict';

angular.module('watererpApp').controller('GroupMasterDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'GroupMaster', 'StatusMaster',
        function($scope, $stateParams, $uibModalInstance, entity, GroupMaster, StatusMaster) {

        $scope.groupMaster = entity;
        $scope.statusmasters = StatusMaster.query();
        $scope.load = function(id) {
            GroupMaster.get({id : id}, function(result) {
                $scope.groupMaster = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:groupMasterUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.groupMaster.id != null) {
                GroupMaster.update($scope.groupMaster, onSaveSuccess, onSaveError);
            } else {
                GroupMaster.save($scope.groupMaster, onSaveSuccess, onSaveError);
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
