'use strict';

angular.module('watererpApp').controller('HydrantComplaintDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'HydrantComplaint', 'WaterLeakageComplaint',
        function($scope, $stateParams, $uibModalInstance, entity, HydrantComplaint, WaterLeakageComplaint) {

        $scope.hydrantComplaint = entity;
        $scope.waterleakagecomplaints = WaterLeakageComplaint.query();
        $scope.load = function(id) {
            HydrantComplaint.get({id : id}, function(result) {
                $scope.hydrantComplaint = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:hydrantComplaintUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.hydrantComplaint.id != null) {
                HydrantComplaint.update($scope.hydrantComplaint, onSaveSuccess, onSaveError);
            } else {
                HydrantComplaint.save($scope.hydrantComplaint, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
