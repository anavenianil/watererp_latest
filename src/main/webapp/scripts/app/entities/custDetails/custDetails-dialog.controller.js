'use strict';

angular.module('watererpApp').controller('CustDetailsDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'CustDetails', 'TariffCategoryMaster',
        function($scope, $stateParams, $uibModalInstance, entity, CustDetails, TariffCategoryMaster) {

        $scope.custDetails = entity;
        $scope.tariffcategorymasters = TariffCategoryMaster.query();
        $scope.load = function(id) {
            CustDetails.get({id : id}, function(result) {
                $scope.custDetails = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:custDetailsUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.custDetails.id != null) {
                CustDetails.update($scope.custDetails, onSaveSuccess, onSaveError);
            } else {
                CustDetails.save($scope.custDetails, onSaveSuccess, onSaveError);
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
        $scope.datePickerForMeterFixDate = {};

        $scope.datePickerForMeterFixDate.status = {
            opened: false
        };

        $scope.datePickerForMeterFixDateOpen = function($event) {
            $scope.datePickerForMeterFixDate.status.opened = true;
        };
}]);
