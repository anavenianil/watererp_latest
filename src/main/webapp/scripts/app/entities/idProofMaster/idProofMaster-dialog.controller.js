'use strict';

angular.module('watererpApp').controller('IdProofMasterDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'IdProofMaster',
        function($scope, $stateParams, $uibModalInstance, entity, IdProofMaster) {

        $scope.idProofMaster = entity;
        $scope.load = function(id) {
            IdProofMaster.get({id : id}, function(result) {
                $scope.idProofMaster = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:idProofMasterUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.idProofMaster.id != null) {
                IdProofMaster.update($scope.idProofMaster, onSaveSuccess, onSaveError);
            } else {
                IdProofMaster.save($scope.idProofMaster, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
