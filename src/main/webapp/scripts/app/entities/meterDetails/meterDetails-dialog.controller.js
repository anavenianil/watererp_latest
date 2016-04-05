'use strict';

angular.module('watererpApp').controller('MeterDetailsDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'MeterDetails',
        function($scope, $stateParams, $uibModalInstance, entity, MeterDetails) {

        $scope.meterDetails = entity;
        $scope.load = function(id) {
            MeterDetails.get({id : id}, function(result) {
                $scope.meterDetails = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:meterDetailsUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.meterDetails.id != null) {
                MeterDetails.update($scope.meterDetails, onSaveSuccess, onSaveError);
            } else {
                MeterDetails.save($scope.meterDetails, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
