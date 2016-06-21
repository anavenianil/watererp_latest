'use strict';

angular.module('watererpApp').controller('BankMasterDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'BankMaster',
        function($scope, $stateParams, $uibModalInstance, entity, BankMaster) {

        $scope.bankMaster = entity;
        $scope.load = function(id) {
            BankMaster.get({id : id}, function(result) {
                $scope.bankMaster = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:bankMasterUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.bankMaster.id != null) {
                BankMaster.update($scope.bankMaster, onSaveSuccess, onSaveError);
            } else {
                BankMaster.save($scope.bankMaster, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
