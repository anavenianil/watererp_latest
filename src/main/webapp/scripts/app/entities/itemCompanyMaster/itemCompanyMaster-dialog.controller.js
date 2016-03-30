'use strict';

angular.module('watererpApp').controller('ItemCompanyMasterDialogController',
        function($scope, $stateParams, $state, ItemCompanyMaster) {

        $scope.itemCompanyMaster = {};
        
        if($stateParams.id!=null){
        	ItemCompanyMaster.get({id : $stateParams.id}, function(result) {
                $scope.itemCompanyMaster = result;
            });
        }
            

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:itemCompanyMasterUpdate', result);
            //$uibModalInstance.close(result);
            $scope.isSaving = false;
            $state.go('itemCompanyMaster');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.itemCompanyMaster.id != null) {
                ItemCompanyMaster.update($scope.itemCompanyMaster, onSaveSuccess, onSaveError);
            } else {
                ItemCompanyMaster.save($scope.itemCompanyMaster, onSaveSuccess, onSaveError);
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
