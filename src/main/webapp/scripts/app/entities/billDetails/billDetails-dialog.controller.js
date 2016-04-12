'use strict';

angular.module('watererpApp').controller('BillDetailsDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'BillDetails',
        function($scope, $stateParams, $uibModalInstance, entity, BillDetails) {

        $scope.billDetails = entity;
        $scope.load = function(id) {
            BillDetails.get({id : id}, function(result) {
                $scope.billDetails = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:billDetailsUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.billDetails.id != null) {
                BillDetails.update($scope.billDetails, onSaveSuccess, onSaveError);
            } else {
                BillDetails.save($scope.billDetails, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForBillDate = {};

        $scope.datePickerForBillDate.status = {
            opened: false
        };

        $scope.datePickerForBillDateOpen = function($event) {
            $scope.datePickerForBillDate.status.opened = true;
        };
        $scope.datePickerForMeterFixDate = {};

        $scope.datePickerForMeterFixDate.status = {
            opened: false
        };

        $scope.datePickerForMeterFixDateOpen = function($event) {
            $scope.datePickerForMeterFixDate.status.opened = true;
        };
        $scope.datePickerForMetReadingDt = {};

        $scope.datePickerForMetReadingDt.status = {
            opened: false
        };

        $scope.datePickerForMetReadingDtOpen = function($event) {
            $scope.datePickerForMetReadingDt.status.opened = true;
        };
}]);
