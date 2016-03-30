'use strict';

angular.module('watererpApp').controller('ItemCategoryMasterDialogController',
        function($scope, $stateParams, ItemCategoryMaster, $state) {

        $scope.itemCategoryMaster = {};
        
        if($stateParams.id !=null){
        	ItemCategoryMaster.get({id : $stateParams.id}, function(result) {
                $scope.itemCategoryMaster = result;
            });
        }
            


        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:itemCategoryMasterUpdate', result);
            //$uibModalInstance.close(result);
            $scope.isSaving = false;
            $state.go('itemCategoryMaster');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.itemCategoryMaster.id != null) {
                ItemCategoryMaster.update($scope.itemCategoryMaster, onSaveSuccess, onSaveError);
            } else {
                ItemCategoryMaster.save($scope.itemCategoryMaster, onSaveSuccess, onSaveError);
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
        $scope.datePickerForCategoryCode = {};

        $scope.datePickerForCategoryCode.status = {
            opened: false
        };

        $scope.datePickerForCategoryCodeOpen = function($event) {
            $scope.datePickerForCategoryCode.status.opened = true;
        };
});
