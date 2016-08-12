'use strict';

angular.module('watererpApp').controller('BurstComplaintDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'BurstComplaint', 'WaterLeakageComplaint',
        function($scope, $stateParams, $uibModalInstance, entity, BurstComplaint, WaterLeakageComplaint) {

        $scope.burstComplaint = entity;
        $scope.waterleakagecomplaints = WaterLeakageComplaint.query();
        $scope.load = function(id) {
            BurstComplaint.get({id : id}, function(result) {
                $scope.burstComplaint = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:burstComplaintUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.burstComplaint.id != null) {
                BurstComplaint.update($scope.burstComplaint, onSaveSuccess, onSaveError);
            } else {
                BurstComplaint.save($scope.burstComplaint, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
