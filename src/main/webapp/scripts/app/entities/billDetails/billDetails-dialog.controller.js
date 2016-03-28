'use strict';

angular.module('waterERPApp').controller('BillDetailsDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'BillDetails',
        function($scope, $stateParams, $uibModalInstance, entity, BillDetails) {

        $scope.billDetails = entity;
        $scope.load = function(id) {
            BillDetails.get({id : id}, function(result) {
                $scope.billDetails = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('waterERPApp:billDetailsUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.billDetails.id != null) {
                BillDetails.update($scope.billDetails, onSaveSuccess, onSaveError);
            } else {
                BillDetails.save($scope.billDetails, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForBill_date = {};

        $scope.datePickerForBill_date.status = {
            opened: false
        };

        $scope.datePickerForBill_dateOpen = function($event) {
            $scope.datePickerForBill_date.status.opened = true;
        };
}]);
