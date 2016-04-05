'use strict';

angular.module('watererpApp').controller('BillDetailsDialogController',
        function($scope, $stateParams, BillDetails, $state) {

        $scope.billDetails = {};
        $scope.load = function(id) {
            BillDetails.get({id : id}, function(result) {
                $scope.billDetails = result;
            });
        };
        
        if($stateParams.id != null){
        	$scope.load($stateParams.id);
        }

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:billDetailsUpdate', result);
            //$uibModalInstance.close(result);
            $scope.isSaving = false;
            $state.go('billDetails');
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
});