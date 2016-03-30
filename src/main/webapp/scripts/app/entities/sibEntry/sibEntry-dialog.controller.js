'use strict';

angular.module('watererpApp').controller('SibEntryDialogController',
        function($scope, $state, $stateParams, SibEntry) {

        $scope.sibEntry = {};
        
       
        $scope.load = function(id) {
            SibEntry.get({id : id}, function(result) {
                $scope.sibEntry = result;
            });
        };

        if($stateParams.id != null){
        	$scope.load($stateParams.id);
        }
        
        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:sibEntryUpdate', result);
            //$uibModalInstance.close(result);
            $scope.isSaving = false;
            $state.go('sibEntry');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.sibEntry.id != null) {
                SibEntry.update($scope.sibEntry, onSaveSuccess, onSaveError);
            } else {
                SibEntry.save($scope.sibEntry, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForSoDate = {};

        $scope.datePickerForSoDate.status = {
            opened: false
        };

        $scope.datePickerForSoDateOpen = function($event) {
            $scope.datePickerForSoDate.status.opened = true;
        };
        $scope.datePickerForDemandDate = {};

        $scope.datePickerForDemandDate.status = {
            opened: false
        };

        $scope.datePickerForDemandDateOpen = function($event) {
            $scope.datePickerForDemandDate.status.opened = true;
        };
        $scope.datePickerForSibDate = {};

        $scope.datePickerForSibDate.status = {
            opened: false
        };

        $scope.datePickerForSibDateOpen = function($event) {
            $scope.datePickerForSibDate.status.opened = true;
        };
        $scope.datePickerForIrDate = {};

        $scope.datePickerForIrDate.status = {
            opened: false
        };

        $scope.datePickerForIrDateOpen = function($event) {
            $scope.datePickerForIrDate.status.opened = true;
        };
        $scope.datePickerForToUser = {};

        $scope.datePickerForToUser.status = {
            opened: false
        };

        $scope.datePickerForToUserOpen = function($event) {
            $scope.datePickerForToUser.status.opened = true;
        };
        $scope.datePickerForFromUser = {};

        $scope.datePickerForFromUser.status = {
            opened: false
        };

        $scope.datePickerForFromUserOpen = function($event) {
            $scope.datePickerForFromUser.status.opened = true;
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
        $scope.datePickerForDcDate = {};

        $scope.datePickerForDcDate.status = {
            opened: false
        };

        $scope.datePickerForDcDateOpen = function($event) {
            $scope.datePickerForDcDate.status.opened = true;
        };
});
