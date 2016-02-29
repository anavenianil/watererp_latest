'use strict';

angular.module('watererpApp').controller('DepartmentsHierarchyDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'DepartmentsHierarchy', 'StatusMaster',
        function($scope, $stateParams, $uibModalInstance, entity, DepartmentsHierarchy, StatusMaster) {

        $scope.departmentsHierarchy = entity;
        $scope.statusmasters = StatusMaster.query();
        $scope.load = function(id) {
            DepartmentsHierarchy.get({id : id}, function(result) {
                $scope.departmentsHierarchy = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:departmentsHierarchyUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.departmentsHierarchy.id != null) {
                DepartmentsHierarchy.update($scope.departmentsHierarchy, onSaveSuccess, onSaveError);
            } else {
                DepartmentsHierarchy.save($scope.departmentsHierarchy, onSaveSuccess, onSaveError);
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
