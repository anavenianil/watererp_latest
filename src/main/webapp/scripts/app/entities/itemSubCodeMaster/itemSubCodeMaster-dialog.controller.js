'use strict';

angular.module('watererpApp').controller('ItemSubCodeMasterDialogController',
        function($scope, $stateParams, ItemSubCodeMaster, $state) {

        $scope.itemSubCodeMaster = {};
        
        if($stateParams.id != null){
        	ItemSubCodeMaster.get({id : $stateParams.id}, function(result) {
                $scope.itemSubCodeMaster = result;
            });
        }
            

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:itemSubCodeMasterUpdate', result);
            //$uibModalInstance.close(result);
            $scope.isSaving = false;
            $state.go('itemSubCodeMaster');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.itemSubCodeMaster.id != null) {
                ItemSubCodeMaster.update($scope.itemSubCodeMaster, onSaveSuccess, onSaveError);
            } else {
                ItemSubCodeMaster.save($scope.itemSubCodeMaster, onSaveSuccess, onSaveError);
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
