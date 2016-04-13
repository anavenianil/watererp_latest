'use strict';

angular.module('watererpApp').controller('BillRunMasterDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'BillRunMaster',
        function($scope, $stateParams, $uibModalInstance, entity, BillRunMaster) {

        $scope.billRunMaster = entity;
        $scope.load = function(id) {
            BillRunMaster.get({id : id}, function(result) {
                $scope.billRunMaster = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:billRunMasterUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.billRunMaster.id != null) {
                BillRunMaster.update($scope.billRunMaster, onSaveSuccess, onSaveError);
            } else {
                BillRunMaster.save($scope.billRunMaster, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForDate = {};

        $scope.datePickerForDate.status = {
            opened: false
        };

        $scope.datePickerForDateOpen = function($event) {
            $scope.datePickerForDate.status.opened = true;
        };
}]);
