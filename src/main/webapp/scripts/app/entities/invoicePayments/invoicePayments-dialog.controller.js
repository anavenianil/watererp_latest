'use strict';

angular.module('watererpApp').controller('InvoicePaymentsDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'InvoicePayments', 'CustDetails', 'BillFullDetails', 'CollDetails',
        function($scope, $stateParams, $uibModalInstance, entity, InvoicePayments, CustDetails, BillFullDetails, CollDetails) {

        $scope.invoicePayments = entity;
        $scope.custdetailss = CustDetails.query();
        $scope.billfulldetailss = BillFullDetails.query();
        $scope.colldetailss = CollDetails.query();
        $scope.load = function(id) {
            InvoicePayments.get({id : id}, function(result) {
                $scope.invoicePayments = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:invoicePaymentsUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.invoicePayments.id != null) {
                InvoicePayments.update($scope.invoicePayments, onSaveSuccess, onSaveError);
            } else {
                InvoicePayments.save($scope.invoicePayments, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
