'use strict';

angular.module('watererpApp').controller('OrgHierarchyDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'OrgHierarchy', 'StatusMaster',
        function($scope, $stateParams, $uibModalInstance, entity, OrgHierarchy, StatusMaster) {

        $scope.orgHierarchy = entity;
        $scope.statusmasters = StatusMaster.query();
        $scope.load = function(id) {
            OrgHierarchy.get({id : id}, function(result) {
                $scope.orgHierarchy = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:orgHierarchyUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.orgHierarchy.id != null) {
                OrgHierarchy.update($scope.orgHierarchy, onSaveSuccess, onSaveError);
            } else {
                OrgHierarchy.save($scope.orgHierarchy, onSaveSuccess, onSaveError);
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
