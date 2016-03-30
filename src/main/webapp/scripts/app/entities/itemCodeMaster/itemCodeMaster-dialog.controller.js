'use strict';

angular.module('watererpApp').controller('ItemCodeMasterDialogController',
        function($scope, $state, $stateParams, ItemCodeMaster, ItemCategoryMaster, ItemSubCategoryMaster) {

        $scope.itemCodeMaster = {};
        $scope.itemcategorymasters = ItemCategoryMaster.query();
        $scope.itemsubcategorymasters = ItemSubCategoryMaster.query();
        
        if($stateParams.id!=null){
            ItemCodeMaster.get({id : $stateParams.id}, function(result) {
                $scope.itemCodeMaster = result;
            });
        }
        

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:itemCodeMasterUpdate', result);
            //$uibModalInstance.close(result);
            $scope.isSaving = false;
            $state.go('itemCodeMaster');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.itemCodeMaster.id != null) {
                ItemCodeMaster.update($scope.itemCodeMaster, onSaveSuccess, onSaveError);
            } else {
                ItemCodeMaster.save($scope.itemCodeMaster, onSaveSuccess, onSaveError);
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
