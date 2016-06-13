'use strict';

angular.module('watererpApp').controller('AdjustmentsDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Adjustments', 'CustDetails', 'BillFullDetails', 'TransactionTypeMaster', 'User', 'ComplaintTypeMaster',
        function($scope, $stateParams, $uibModalInstance, entity, Adjustments, CustDetails, BillFullDetails, TransactionTypeMaster, User, ComplaintTypeMaster) {

        $scope.adjustments = entity;
        $scope.custdetailss = CustDetails.query();
        $scope.billfulldetailss = BillFullDetails.query();
        $scope.transactiontypemasters = TransactionTypeMaster.query();
        $scope.users = User.query();
        $scope.complainttypemasters = ComplaintTypeMaster.query();
        $scope.load = function(id) {
            Adjustments.get({id : id}, function(result) {
                $scope.adjustments = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:adjustmentsUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.adjustments.id != null) {
                Adjustments.update($scope.adjustments, onSaveSuccess, onSaveError);
            } else {
                Adjustments.save($scope.adjustments, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForTxnTime = {};

        $scope.datePickerForTxnTime.status = {
            opened: false
        };

        $scope.datePickerForTxnTimeOpen = function($event) {
            $scope.datePickerForTxnTime.status.opened = true;
        };
}]);
