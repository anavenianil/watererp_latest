'use strict';

angular.module('watererpApp').controller('CustomerDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Customer', 'TariffCategoryMaster', 'IdProofMaster', 'StatusMaster', 'PipeSizeMaster',
        function($scope, $stateParams, $uibModalInstance, entity, Customer, TariffCategoryMaster, IdProofMaster, StatusMaster, PipeSizeMaster) {

        $scope.customer = entity;
        $scope.tariffcategorymasters = TariffCategoryMaster.query();
        $scope.idproofmasters = IdProofMaster.query();
        $scope.statusmasters = StatusMaster.query();
        $scope.pipesizemasters = PipeSizeMaster.query();
        $scope.load = function(id) {
            Customer.get({id : id}, function(result) {
                $scope.customer = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:customerUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.customer.id != null) {
                Customer.update($scope.customer, onSaveSuccess, onSaveError);
            } else {
                Customer.save($scope.customer, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForRequestedDate = {};

        $scope.datePickerForRequestedDate.status = {
            opened: false
        };

        $scope.datePickerForRequestedDateOpen = function($event) {
            $scope.datePickerForRequestedDate.status.opened = true;
        };
        $scope.datePickerForApprovedDate = {};

        $scope.datePickerForApprovedDate.status = {
            opened: false
        };

        $scope.datePickerForApprovedDateOpen = function($event) {
            $scope.datePickerForApprovedDate.status.opened = true;
        };
}]);
