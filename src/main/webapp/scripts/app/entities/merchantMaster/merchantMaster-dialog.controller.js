'use strict';

angular.module('watererpApp').controller('MerchantMasterDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'MerchantMaster',
        function($scope, $stateParams, $uibModalInstance, entity, MerchantMaster) {

        $scope.merchantMaster = entity;
        $scope.load = function(id) {
            MerchantMaster.get({id : id}, function(result) {
                $scope.merchantMaster = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:merchantMasterUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.merchantMaster.id != null) {
                MerchantMaster.update($scope.merchantMaster, onSaveSuccess, onSaveError);
            } else {
                MerchantMaster.save($scope.merchantMaster, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
