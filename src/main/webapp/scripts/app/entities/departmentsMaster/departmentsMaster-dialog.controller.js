'use strict';

angular.module('watererpApp').controller('DepartmentsMasterDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'DepartmentsMaster', 'DepartmentsHierarchy', 'DepartmentTypeMaster', 'StatusMaster',
        function($scope, $stateParams, $uibModalInstance, entity, DepartmentsMaster, DepartmentsHierarchy, DepartmentTypeMaster, StatusMaster) {

        $scope.departmentsMaster = entity;
        $scope.departmentshierarchys = DepartmentsHierarchy.query();
        $scope.departmenttypemasters = DepartmentTypeMaster.query();
        $scope.statusmasters = StatusMaster.query();
        $scope.load = function(id) {
            DepartmentsMaster.get({id : id}, function(result) {
                $scope.departmentsMaster = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:departmentsMasterUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.departmentsMaster.id != null) {
                DepartmentsMaster.update($scope.departmentsMaster, onSaveSuccess, onSaveError);
            } else {
                DepartmentsMaster.save($scope.departmentsMaster, onSaveSuccess, onSaveError);
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
