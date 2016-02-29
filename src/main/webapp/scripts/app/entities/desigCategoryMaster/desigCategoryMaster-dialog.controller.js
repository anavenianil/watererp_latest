'use strict';

angular.module('watererpApp').controller('DesigCategoryMasterDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'DesigCategoryMaster', 'StatusMaster',
        function($scope, $stateParams, $uibModalInstance, entity, DesigCategoryMaster, StatusMaster) {

        $scope.desigCategoryMaster = entity;
        $scope.statusmasters = StatusMaster.query();
        $scope.load = function(id) {
            DesigCategoryMaster.get({id : id}, function(result) {
                $scope.desigCategoryMaster = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:desigCategoryMasterUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.desigCategoryMaster.id != null) {
                DesigCategoryMaster.update($scope.desigCategoryMaster, onSaveSuccess, onSaveError);
            } else {
                DesigCategoryMaster.save($scope.desigCategoryMaster, onSaveSuccess, onSaveError);
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
