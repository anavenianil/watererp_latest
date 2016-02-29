'use strict';

angular.module('watererpApp').controller('ApplicationTxnDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'ApplicationTxn', 'ApplicationTypeMaster', 'ConnectionTypeMaster', 'CtegoryMaster', 'PipeSizeMaster', 'SewerSize', 'FileNumber', 'Customer',
        function($scope, $stateParams, $uibModalInstance, entity, ApplicationTxn, ApplicationTypeMaster, ConnectionTypeMaster, CtegoryMaster, PipeSizeMaster, SewerSize, FileNumber, Customer) {

        $scope.applicationTxn = entity;
        $scope.applicationtypemasters = ApplicationTypeMaster.query();
        $scope.connectiontypemasters = ConnectionTypeMaster.query();
        $scope.ctegorymasters = CtegoryMaster.query();
        $scope.pipesizemasters = PipeSizeMaster.query();
        $scope.sewersizes = SewerSize.query();
        $scope.filenumbers = FileNumber.query();
        $scope.customers = Customer.query();
        $scope.load = function(id) {
            ApplicationTxn.get({id : id}, function(result) {
                $scope.applicationTxn = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:applicationTxnUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.applicationTxn.id != null) {
                ApplicationTxn.update($scope.applicationTxn, onSaveSuccess, onSaveError);
            } else {
                ApplicationTxn.save($scope.applicationTxn, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForCreatedDate = {};

        $scope.datePickerForCreatedDate.status = {
            opened: false
        };

        $scope.datePickerForCreatedDateOpen = function($event) {
            $scope.datePickerForCreatedDate.status.opened = true;
        };
        $scope.datePickerForUpdatedDate = {};

        $scope.datePickerForUpdatedDate.status = {
            opened: false
        };

        $scope.datePickerForUpdatedDateOpen = function($event) {
            $scope.datePickerForUpdatedDate.status.opened = true;
        };
}]);
