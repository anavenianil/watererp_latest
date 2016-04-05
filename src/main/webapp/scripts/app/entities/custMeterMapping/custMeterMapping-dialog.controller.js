'use strict';

angular.module('waterERPApp').controller('CustMeterMappingDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'CustMeterMapping', 'CustDetails', 'MeterDetails',
        function($scope, $stateParams, $uibModalInstance, entity, CustMeterMapping, CustDetails, MeterDetails) {

        $scope.custMeterMapping = entity;
        $scope.custdetailss = CustDetails.query();
        $scope.meterdetailss = MeterDetails.query();
        $scope.load = function(id) {
            CustMeterMapping.get({id : id}, function(result) {
                $scope.custMeterMapping = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('waterERPApp:custMeterMappingUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.custMeterMapping.id != null) {
                CustMeterMapping.update($scope.custMeterMapping, onSaveSuccess, onSaveError);
            } else {
                CustMeterMapping.save($scope.custMeterMapping, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForFromDate = {};

        $scope.datePickerForFromDate.status = {
            opened: false
        };

        $scope.datePickerForFromDateOpen = function($event) {
            $scope.datePickerForFromDate.status.opened = true;
        };
        $scope.datePickerForToDate = {};

        $scope.datePickerForToDate.status = {
            opened: false
        };

        $scope.datePickerForToDateOpen = function($event) {
            $scope.datePickerForToDate.status.opened = true;
        };
}]);
