'use strict';

angular.module('waterERPApp').controller('CollDetailsDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'CollDetails',
        function($scope, $stateParams, $uibModalInstance, entity, CollDetails) {

        $scope.collDetails = entity;
        $scope.load = function(id) {
            CollDetails.get({id : id}, function(result) {
                $scope.collDetails = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('waterERPApp:collDetailsUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.collDetails.id != null) {
                CollDetails.update($scope.collDetails, onSaveSuccess, onSaveError);
            } else {
                CollDetails.save($scope.collDetails, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForReceiptDt = {};

        $scope.datePickerForReceiptDt.status = {
            opened: false
        };

        $scope.datePickerForReceiptDtOpen = function($event) {
            $scope.datePickerForReceiptDt.status.opened = true;
        };
        $scope.datePickerForInstrDt = {};

        $scope.datePickerForInstrDt.status = {
            opened: false
        };

        $scope.datePickerForInstrDtOpen = function($event) {
            $scope.datePickerForInstrDt.status.opened = true;
        };
        $scope.datePickerForCollTime = {};

        $scope.datePickerForCollTime.status = {
            opened: false
        };

        $scope.datePickerForCollTimeOpen = function($event) {
            $scope.datePickerForCollTime.status.opened = true;
        };
}]);
