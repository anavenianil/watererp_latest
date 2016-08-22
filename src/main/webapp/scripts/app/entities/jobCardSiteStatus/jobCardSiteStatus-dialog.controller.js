'use strict';

angular.module('watererpApp').controller('JobCardSiteStatusDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'JobCardSiteStatus', 'WaterLeakageComplaint',
        function($scope, $stateParams, $uibModalInstance, entity, JobCardSiteStatus, WaterLeakageComplaint) {

        $scope.jobCardSiteStatus = entity;
        $scope.waterleakagecomplaints = WaterLeakageComplaint.query();
        $scope.load = function(id) {
            JobCardSiteStatus.get({id : id}, function(result) {
                $scope.jobCardSiteStatus = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:jobCardSiteStatusUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.jobCardSiteStatus.id != null) {
                JobCardSiteStatus.update($scope.jobCardSiteStatus, onSaveSuccess, onSaveError);
            } else {
                JobCardSiteStatus.save($scope.jobCardSiteStatus, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
