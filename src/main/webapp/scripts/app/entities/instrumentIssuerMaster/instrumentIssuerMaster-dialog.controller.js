'use strict';

angular.module('watererpApp').controller('InstrumentIssuerMasterDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'InstrumentIssuerMaster',
        function($scope, $stateParams, $uibModalInstance, entity, InstrumentIssuerMaster) {

        $scope.instrumentIssuerMaster = entity;
        $scope.load = function(id) {
            InstrumentIssuerMaster.get({id : id}, function(result) {
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
                InstrumentIssuerMaster.update($scope.instrumentIssuerMaster, onSaveSuccess, onSaveError);
            } else {
                InstrumentIssuerMaster.save($scope.instrumentIssuerMaster, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
