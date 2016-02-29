'use strict';

angular.module('watererpApp').controller('DepartmentTypeMasterDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'DepartmentTypeMaster', 'StatusMaster',
        function($scope, $stateParams, $uibModalInstance, entity, DepartmentTypeMaster, StatusMaster) {

        $scope.departmentTypeMaster = entity;
        $scope.statusmasters = StatusMaster.query();
        $scope.load = function(id) {
            DepartmentTypeMaster.get({id : id}, function(result) {
                $scope.departmentTypeMaster = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:departmentTypeMasterUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.departmentTypeMaster.id != null) {
                DepartmentTypeMaster.update($scope.departmentTypeMaster, onSaveSuccess, onSaveError);
            } else {
                DepartmentTypeMaster.save($scope.departmentTypeMaster, onSaveSuccess, onSaveError);
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
