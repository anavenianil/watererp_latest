'use strict';

angular.module('watererpApp').controller('TariffTypeMasterDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'TariffTypeMaster',
        function($scope, $stateParams, $uibModalInstance, entity, TariffTypeMaster) {

        $scope.tariffTypeMaster = entity;
        $scope.load = function(id) {
            TariffTypeMaster.get({id : id}, function(result) {
                $scope.tariffTypeMaster = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:tariffTypeMasterUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.tariffTypeMaster.id != null) {
                TariffTypeMaster.update($scope.tariffTypeMaster, onSaveSuccess, onSaveError);
            } else {
                TariffTypeMaster.save($scope.tariffTypeMaster, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
