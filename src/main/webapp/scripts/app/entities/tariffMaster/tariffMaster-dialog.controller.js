'use strict';

angular.module('waterERPApp').controller('TariffMasterDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'TariffMaster', 'TariffCategoryMaster',
        function($scope, $stateParams, $uibModalInstance, entity, TariffMaster, TariffCategoryMaster) {

        $scope.tariffMaster = entity;
        $scope.tariffcategorymasters = TariffCategoryMaster.query();
        $scope.load = function(id) {
            TariffMaster.get({id : id}, function(result) {
                $scope.tariffMaster = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('waterERPApp:tariffMasterUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.tariffMaster.id != null) {
                TariffMaster.update($scope.tariffMaster, onSaveSuccess, onSaveError);
            } else {
                TariffMaster.save($scope.tariffMaster, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForValidFrom = {};

        $scope.datePickerForValidFrom.status = {
            opened: false
        };

        $scope.datePickerForValidFromOpen = function($event) {
            $scope.datePickerForValidFrom.status.opened = true;
        };
        $scope.datePickerForValidTo = {};

        $scope.datePickerForValidTo.status = {
            opened: false
        };

        $scope.datePickerForValidToOpen = function($event) {
            $scope.datePickerForValidTo.status.opened = true;
        };
}]);
