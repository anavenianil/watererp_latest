'use strict';

angular.module('watererpApp').controller('BankMasterDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'BankMaster',
        function($scope, $stateParams, $uibModalInstance, entity, BankMaster) {

        $scope.instrumentIssuerMaster = entity;
        $scope.load = function(id) {
            BankMaster.get({id : id}, function(result) {
                $scope.instrumentIssuerMaster = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:instrumentIssuerMasterUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.instrumentIssuerMaster.id != null) {
                BankMaster.update($scope.instrumentIssuerMaster, onSaveSuccess, onSaveError);
            } else {
                BankMaster.save($scope.instrumentIssuerMaster, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
