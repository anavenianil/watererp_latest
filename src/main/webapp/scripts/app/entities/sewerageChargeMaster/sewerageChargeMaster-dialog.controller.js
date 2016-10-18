'use strict';

angular.module('watererpApp').controller('SewerageChargeMasterDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'SewerageChargeMaster', 'TariffCategoryMaster',
        function($scope, $stateParams, $uibModalInstance, entity, SewerageChargeMaster, TariffCategoryMaster) {

        $scope.sewerageChargeMaster = entity;
        $scope.tariffcategorymasters = TariffCategoryMaster.query();
        $scope.load = function(id) {
            SewerageChargeMaster.get({id : id}, function(result) {
                $scope.sewerageChargeMaster = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:sewerageChargeMasterUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.sewerageChargeMaster.id != null) {
                SewerageChargeMaster.update($scope.sewerageChargeMaster, onSaveSuccess, onSaveError);
            } else {
                SewerageChargeMaster.save($scope.sewerageChargeMaster, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
