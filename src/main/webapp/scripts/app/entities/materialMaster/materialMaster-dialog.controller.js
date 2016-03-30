'use strict';

angular.module('watererpApp').controller('MaterialMasterDialogController',
        function($scope, $state, $stateParams, MaterialMaster) {

        $scope.materialMaster = {};

        if($stateParams.id != null){
        	MaterialMaster.get({id : $stateParams.id}, function(result) {
                $scope.materialMaster = result;
            });
        }
           

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:materialMasterUpdate', result);
            //$uibModalInstance.close(result);
            $scope.isSaving = false;
            $state.go('materialMaster');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.materialMaster.id != null) {
                MaterialMaster.update($scope.materialMaster, onSaveSuccess, onSaveError);
            } else {
                MaterialMaster.save($scope.materialMaster, onSaveSuccess, onSaveError);
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
