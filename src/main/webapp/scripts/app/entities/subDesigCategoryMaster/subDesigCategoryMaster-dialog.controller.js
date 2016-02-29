'use strict';

angular.module('watererpApp').controller('SubDesigCategoryMasterDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'SubDesigCategoryMaster', 'StatusMaster',
        function($scope, $stateParams, $uibModalInstance, entity, SubDesigCategoryMaster, StatusMaster) {

        $scope.subDesigCategoryMaster = entity;
        $scope.statusmasters = StatusMaster.query();
        $scope.load = function(id) {
            SubDesigCategoryMaster.get({id : id}, function(result) {
                $scope.subDesigCategoryMaster = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:subDesigCategoryMasterUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.subDesigCategoryMaster.id != null) {
                SubDesigCategoryMaster.update($scope.subDesigCategoryMaster, onSaveSuccess, onSaveError);
            } else {
                SubDesigCategoryMaster.save($scope.subDesigCategoryMaster, onSaveSuccess, onSaveError);
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
