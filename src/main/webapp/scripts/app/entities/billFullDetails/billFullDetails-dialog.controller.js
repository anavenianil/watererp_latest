'use strict';

angular.module('waterERPApp').controller('BillFullDetailsDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'BillFullDetails',
        function($scope, $stateParams, $uibModalInstance, entity, BillFullDetails) {

        $scope.billFullDetails = entity;
        $scope.load = function(id) {
            BillFullDetails.get({id : id}, function(result) {
                $scope.billFullDetails = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('waterERPApp:billFullDetailsUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.billFullDetails.id != null) {
                BillFullDetails.update($scope.billFullDetails, onSaveSuccess, onSaveError);
            } else {
                BillFullDetails.save($scope.billFullDetails, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForConnDate = {};

        $scope.datePickerForConnDate.status = {
            opened: false
        };

        $scope.datePickerForConnDateOpen = function($event) {
            $scope.datePickerForConnDate.status.opened = true;
        };
        $scope.datePickerForPrevBillMonth = {};

        $scope.datePickerForPrevBillMonth.status = {
            opened: false
        };

        $scope.datePickerForPrevBillMonthOpen = function($event) {
            $scope.datePickerForPrevBillMonth.status.opened = true;
        };
        $scope.datePickerForMetReadingDt = {};

        $scope.datePickerForMetReadingDt.status = {
            opened: false
        };

        $scope.datePickerForMetReadingDtOpen = function($event) {
            $scope.datePickerForMetReadingDt.status.opened = true;
        };
        $scope.datePickerForMetReadingMo = {};

        $scope.datePickerForMetReadingMo.status = {
            opened: false
        };

        $scope.datePickerForMetReadingMoOpen = function($event) {
            $scope.datePickerForMetReadingMo.status.opened = true;
        };
        $scope.datePickerForLastPymtDt = {};

        $scope.datePickerForLastPymtDt.status = {
            opened: false
        };

        $scope.datePickerForLastPymtDtOpen = function($event) {
            $scope.datePickerForLastPymtDt.status.opened = true;
        };
        $scope.datePickerForBillDate = {};

        $scope.datePickerForBillDate.status = {
            opened: false
        };

        $scope.datePickerForBillDateOpen = function($event) {
            $scope.datePickerForBillDate.status.opened = true;
        };
}]);
