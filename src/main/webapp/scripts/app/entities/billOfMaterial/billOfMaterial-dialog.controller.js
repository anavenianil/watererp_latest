'use strict';

angular.module('watererpApp').controller('BillOfMaterialDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'BillOfMaterial', 'ApplicationTxn', 'PaymentTypes',
        function($scope, $stateParams, $uibModalInstance, entity, BillOfMaterial, ApplicationTxn, PaymentTypes) {

        $scope.billOfMaterial = entity;
        $scope.applicationtxns = ApplicationTxn.query();
        $scope.paymenttypess = PaymentTypes.query();
        $scope.load = function(id) {
            BillOfMaterial.get({id : id}, function(result) {
                $scope.billOfMaterial = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:billOfMaterialUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
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
        $scope.datePickerForCheckDate = {};

        $scope.datePickerForCheckDate.status = {
            opened: false
        };

        $scope.datePickerForCheckDateOpen = function($event) {
            $scope.datePickerForCheckDate.status.opened = true;
        };
}]);
