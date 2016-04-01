'use strict';

angular.module('watererpApp').controller('BillOfMaterialDialogController',
        function($scope, $state, $stateParams, BillOfMaterial, ApplicationTxn, PaymentTypes) {

        $scope.billOfMaterial = {};
        $scope.applicationtxns = ApplicationTxn.query();
        $scope.paymenttypess = PaymentTypes.query();
        
        $scope.load = function(id) {
            BillOfMaterial.get({id : id}, function(result) {
                $scope.billOfMaterial = result;
            });
        };
        
        if($stateParams.id != null){
        	$scope.load($stateParams.id);
        }

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:billOfMaterialUpdate', result);
            $scope.isSaving = false;
            $state.go('billOfMaterial');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.billOfMaterial.id != null) {
                BillOfMaterial.update($scope.billOfMaterial, onSaveSuccess, onSaveError);
            } else {
                BillOfMaterial.save($scope.billOfMaterial, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForCheckOrDdDate = {};

        $scope.datePickerForCheckOrDdDate.status = {
            opened: false
        };

        $scope.datePickerForCheckOrDdDateOpen = function($event) {
            $scope.datePickerForCheckOrDdDate.status.opened = true;
        };
        $scope.datePickerForBillDate = {};

        $scope.datePickerForBillDate.status = {
            opened: false
        };

        $scope.datePickerForBillDateOpen = function($event) {
            $scope.datePickerForBillDate.status.opened = true;
        };
});
