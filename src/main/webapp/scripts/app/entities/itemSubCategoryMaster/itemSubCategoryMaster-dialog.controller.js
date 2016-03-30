'use strict';

angular.module('watererpApp').controller('ItemSubCategoryMasterDialogController',
        function($scope, $stateParams, $state, ItemSubCategoryMaster, ItemCategoryMaster) {

        $scope.itemSubCategoryMaster = {};
        $scope.itemcategorymasters = ItemCategoryMaster.query();

        if($stateParams.id!= null){
            ItemSubCategoryMaster.get({id : $stateParams.id}, function(result) {
                $scope.itemSubCategoryMaster = result;
            });
        }

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:itemSubCategoryMasterUpdate', result);
            //$uibModalInstance.close(result);
            $scope.isSaving = false;
            $state.go('itemSubCategoryMaster');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.itemSubCategoryMaster.id != null) {
                ItemSubCategoryMaster.update($scope.itemSubCategoryMaster, onSaveSuccess, onSaveError);
            } else {
                ItemSubCategoryMaster.save($scope.itemSubCategoryMaster, onSaveSuccess, onSaveError);
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
});
