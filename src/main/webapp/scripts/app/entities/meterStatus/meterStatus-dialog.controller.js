'use strict';

angular.module('watererpApp').controller('MeterStatusDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'MeterStatus',
        function($scope, $stateParams, $uibModalInstance, entity, MeterStatus) {

        $scope.meterStatus = entity;
        $scope.load = function(id) {
            MeterStatus.get({id : id}, function(result) {
                $scope.meterStatus = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:meterStatusUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.meterStatus.id != null) {
                MeterStatus.update($scope.meterStatus, onSaveSuccess, onSaveError);
            } else {
                MeterStatus.save($scope.meterStatus, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
