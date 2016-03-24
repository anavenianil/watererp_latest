'use strict';

angular.module('watererpApp').controller('DivisionMasterDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'DivisionMaster',
        function($scope, $stateParams, $uibModalInstance, entity, DivisionMaster) {

        $scope.divisionMaster = entity;
        $scope.load = function(id) {
            DivisionMaster.get({id : id}, function(result) {
                $scope.divisionMaster = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:divisionMasterUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.divisionMaster.id != null) {
                DivisionMaster.update($scope.divisionMaster, onSaveSuccess, onSaveError);
            } else {
                DivisionMaster.save($scope.divisionMaster, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
