'use strict';

angular.module('watererpApp').controller('JobCardItemRequirementDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'JobCardItemRequirement', 'MaterialMaster', 'Uom', 'WaterLeakageComplaint',
        function($scope, $stateParams, $uibModalInstance, entity, JobCardItemRequirement, MaterialMaster, Uom, WaterLeakageComplaint) {

        $scope.jobCardItemRequirement = entity;
        $scope.materialmasters = MaterialMaster.query();
        $scope.uoms = Uom.query();
        $scope.waterleakagecomplaints = WaterLeakageComplaint.query();
        $scope.load = function(id) {
            JobCardItemRequirement.get({id : id}, function(result) {
                $scope.jobCardItemRequirement = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:jobCardItemRequirementUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.jobCardItemRequirement.id != null) {
                JobCardItemRequirement.update($scope.jobCardItemRequirement, onSaveSuccess, onSaveError);
            } else {
                JobCardItemRequirement.save($scope.jobCardItemRequirement, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
