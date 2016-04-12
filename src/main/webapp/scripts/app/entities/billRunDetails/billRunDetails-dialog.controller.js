'use strict';

angular.module('watererpApp').controller('BillRunDetailsDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'BillRunDetails', 'BillFullDetails',
        function($scope, $stateParams, $uibModalInstance, entity, BillRunDetails, BillFullDetails) {

        $scope.billRunDetails = entity;
        $scope.billfulldetailss = BillFullDetails.query();
        $scope.load = function(id) {
            BillRunDetails.get({id : id}, function(result) {
                $scope.billRunDetails = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:billRunDetailsUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.billRunDetails.id != null) {
                BillRunDetails.update($scope.billRunDetails, onSaveSuccess, onSaveError);
            } else {
                BillRunDetails.save($scope.billRunDetails, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForFromDt = {};

        $scope.datePickerForFromDt.status = {
            opened: false
        };

        $scope.datePickerForFromDtOpen = function($event) {
            $scope.datePickerForFromDt.status.opened = true;
        };
        $scope.datePickerForToDt = {};

        $scope.datePickerForToDt.status = {
            opened: false
        };

        $scope.datePickerForToDtOpen = function($event) {
            $scope.datePickerForToDt.status.opened = true;
        };
}]);
