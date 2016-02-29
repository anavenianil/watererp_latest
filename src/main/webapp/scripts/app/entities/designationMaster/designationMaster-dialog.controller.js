'use strict';

angular.module('watererpApp').controller('DesignationMasterDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'DesignationMaster', 'StatusMaster',
        function($scope, $stateParams, $uibModalInstance, entity, DesignationMaster, StatusMaster) {

        $scope.designationMaster = entity;
        $scope.statusmasters = StatusMaster.query();
        $scope.load = function(id) {
            DesignationMaster.get({id : id}, function(result) {
                $scope.designationMaster = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:designationMasterUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.designationMaster.id != null) {
                DesignationMaster.update($scope.designationMaster, onSaveSuccess, onSaveError);
            } else {
                DesignationMaster.save($scope.designationMaster, onSaveSuccess, onSaveError);
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
