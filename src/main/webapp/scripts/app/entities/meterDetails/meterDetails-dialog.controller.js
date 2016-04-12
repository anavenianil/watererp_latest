'use strict';

angular.module('watererpApp').controller('MeterDetailsDialogController',
        function($scope, $stateParams, MeterDetails) {

        $scope.meterDetails = {};
        $scope.load = function(id) {
            MeterDetails.get({id : id}, function(result) {
                $scope.meterDetails = result;
            });
        };
        
        if($stateParams.id != null){
        	$scope.load($stateParams.id);
        }

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:meterDetailsUpdate', result);
            //$uibModalInstance.close(result);
            $scope.isSaving = false;
            $state.go('meterDetails');
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
});
