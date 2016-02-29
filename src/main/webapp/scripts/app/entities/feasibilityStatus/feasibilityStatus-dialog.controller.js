'use strict';

angular.module('watererpApp').controller('FeasibilityStatusDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'FeasibilityStatus',
        function($scope, $stateParams, $uibModalInstance, entity, FeasibilityStatus) {

        $scope.feasibilityStatus = entity;
        $scope.load = function(id) {
            FeasibilityStatus.get({id : id}, function(result) {
                $scope.feasibilityStatus = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:feasibilityStatusUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.feasibilityStatus.id != null) {
                FeasibilityStatus.update($scope.feasibilityStatus, onSaveSuccess, onSaveError);
            } else {
                FeasibilityStatus.save($scope.feasibilityStatus, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
