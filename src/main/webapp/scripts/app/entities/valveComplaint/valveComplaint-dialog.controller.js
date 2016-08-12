'use strict';

angular.module('watererpApp').controller('ValveComplaintDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'ValveComplaint', 'WaterLeakageComplaint',
        function($scope, $stateParams, $uibModalInstance, entity, ValveComplaint, WaterLeakageComplaint) {

        $scope.valveComplaint = entity;
        $scope.waterleakagecomplaints = WaterLeakageComplaint.query();
        $scope.load = function(id) {
            ValveComplaint.get({id : id}, function(result) {
                $scope.valveComplaint = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:valveComplaintUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.valveComplaint.id != null) {
                ValveComplaint.update($scope.valveComplaint, onSaveSuccess, onSaveError);
            } else {
                ValveComplaint.save($scope.valveComplaint, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForClosedTime = {};

        $scope.datePickerForClosedTime.status = {
            opened: false
        };

        $scope.datePickerForClosedTimeOpen = function($event) {
            $scope.datePickerForClosedTime.status.opened = true;
        };
        $scope.datePickerForOpenTime = {};

        $scope.datePickerForOpenTime.status = {
            opened: false
        };

        $scope.datePickerForOpenTimeOpen = function($event) {
            $scope.datePickerForOpenTime.status.opened = true;
        };
}]);
