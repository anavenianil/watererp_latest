'use strict';

angular.module('watererpApp').controller('TariffCategoryMasterDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'TariffCategoryMaster',
        function($scope, $stateParams, $uibModalInstance, entity, TariffCategoryMaster) {

        $scope.tariffCategoryMaster = entity;
        $scope.load = function(id) {
            TariffCategoryMaster.get({id : id}, function(result) {
                $scope.tariffCategoryMaster = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:tariffCategoryMasterUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.tariffCategoryMaster.id != null) {
                TariffCategoryMaster.update($scope.tariffCategoryMaster, onSaveSuccess, onSaveError);
            } else {
                TariffCategoryMaster.save($scope.tariffCategoryMaster, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
