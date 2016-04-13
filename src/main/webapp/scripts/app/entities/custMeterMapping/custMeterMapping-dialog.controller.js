'use strict';

angular.module('watererpApp').controller('CustMeterMappingDialogController',
        function($scope, $stateParams, $state, CustMeterMapping, CustDetails, MeterDetails, ApplicationTxn) {

        $scope.custMeterMapping = {};
        $scope.custdetailss = CustDetails.query();
        $scope.meterdetailss = MeterDetails.query();
        
        $scope.load = function(id) {
            CustMeterMapping.get({id : id}, function(result) {
                $scope.custMeterMapping = result;
            });
        };
        
        if($stateParams.applicationTxnId != null){
        	console.log("applicationTxnId: "+$stateParams.applicationTxnId);
        	ApplicationTxn.get({id : $stateParams.applicationTxnId}, function(result) {
                $scope.applicationTxn = result;
            });	
        }
        
        
        if($stateParams.id != null){
        	$scope.load($stateParams.id);
        }

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:custMeterMappingUpdate', result);
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
});
