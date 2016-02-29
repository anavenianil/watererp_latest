'use strict';

angular.module('watererpApp').controller('ManageCashPointDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'ManageCashPoint', 'TransactionTypeMaster', 'CashBookMaster', 'PaymentTypes', 'FileNumber', 'Customer',
        function($scope, $stateParams, $uibModalInstance, entity, ManageCashPoint, TransactionTypeMaster, CashBookMaster, PaymentTypes, FileNumber, Customer) {

        $scope.manageCashPoint = entity;
        $scope.transactiontypemasters = TransactionTypeMaster.query();
        $scope.cashbookmasters = CashBookMaster.query();
        $scope.paymenttypess = PaymentTypes.query();
        $scope.filenumbers = FileNumber.query();
        $scope.customers = Customer.query();
        $scope.load = function(id) {
            ManageCashPoint.get({id : id}, function(result) {
                $scope.manageCashPoint = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:manageCashPointUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.manageCashPoint.id != null) {
                ManageCashPoint.update($scope.manageCashPoint, onSaveSuccess, onSaveError);
            } else {
                ManageCashPoint.save($scope.manageCashPoint, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForTodayDate = {};

        $scope.datePickerForTodayDate.status = {
            opened: false
        };

        $scope.datePickerForTodayDateOpen = function($event) {
            $scope.datePickerForTodayDate.status.opened = true;
        };
}]);
