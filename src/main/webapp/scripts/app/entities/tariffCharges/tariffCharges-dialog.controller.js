'use strict';

angular.module('watererpApp').controller('TariffChargesDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'TariffCharges', 'TariffMaster', 'TariffTypeMaster',
        function($scope, $stateParams, $uibModalInstance, entity, TariffCharges, TariffMaster, TariffTypeMaster) {

        $scope.tariffCharges = entity;
        $scope.tariffmasters = TariffMaster.query();
        $scope.tarifftypemasters = TariffTypeMaster.query();
        $scope.load = function(id) {
            TariffCharges.get({id : id}, function(result) {
                $scope.tariffCharges = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:tariffChargesUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.tariffCharges.id != null) {
                TariffCharges.update($scope.tariffCharges, onSaveSuccess, onSaveError);
            } else {
                TariffCharges.save($scope.tariffCharges, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
