'use strict';

angular.module('watererpApp').controller('ApprovalDetailsDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'ApprovalDetails', 'Customer', 'FeasibilityStatus', 'DesignationMaster', 'ApplicationTxn',
        function($scope, $stateParams, $uibModalInstance, entity, ApprovalDetails, Customer, FeasibilityStatus, DesignationMaster, ApplicationTxn) {

        $scope.approvalDetails = entity;
        $scope.customers = Customer.query();
        $scope.feasibilitystatuss = FeasibilityStatus.query();
        $scope.designationmasters = DesignationMaster.query();
        $scope.applicationtxns = ApplicationTxn.query();
        $scope.load = function(id) {
            ApprovalDetails.get({id : id}, function(result) {
                $scope.approvalDetails = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:approvalDetailsUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.approvalDetails.id != null) {
                ApprovalDetails.update($scope.approvalDetails, onSaveSuccess, onSaveError);
            } else {
                ApprovalDetails.save($scope.approvalDetails, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForApprovedDate = {};

        $scope.datePickerForApprovedDate.status = {
            opened: false
        };

        $scope.datePickerForApprovedDateOpen = function($event) {
            $scope.datePickerForApprovedDate.status.opened = true;
        };
}]);
