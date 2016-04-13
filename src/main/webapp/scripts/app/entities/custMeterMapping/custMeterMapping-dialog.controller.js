'use strict';

angular.module('watererpApp').controller('CustMeterMappingDialogController',
        function($scope, $stateParams, $state, CustMeterMapping, CustDetails, MeterDetails, ApplicationTxn, ApplicationTxnService) {

        $scope.custMeterMapping = {};
        $scope.custdetailss = CustDetails.query();
        $scope.meterdetailss = MeterDetails.query();
        $scope.applicationTxn = {};
        $scope.applicationTxnId = $stateParams.applicationTxnId;
        
        $scope.load = function(id) {
            CustMeterMapping.get({id : id}, function(result) {
                $scope.custMeterMapping = result;
            });
        };
        
        if($stateParams.id != null){
        	$scope.load($stateParams);
        }
        
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
            $state.go('applicationTxn');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
        	//ApplicationTxnService.approveRequest($scope.applicationTxnId, $scope.applicationTxn.remarks);
        	ApplicationTxn.update($scope.applicationTxn);
        	console.log(JSON.stringify($scope.applicationTxn));
        	
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
