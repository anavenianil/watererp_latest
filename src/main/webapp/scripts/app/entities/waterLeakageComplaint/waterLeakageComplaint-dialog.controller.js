'use strict';

angular.module('watererpApp').controller('WaterLeakageComplaintDialogController',
    
        function($scope, $stateParams, /*entity,*/ WaterLeakageComplaint, DivisionMaster, StreetMaster, JobCardItemRequirement) {

		if($stateParams.id!=null){
			$scope.waterLeakageComplaint = entity;
		}
		else{
			$scope.waterLeakageComplaint = {};
		}
        
        
        $scope.divisionmasters = DivisionMaster.query();
        $scope.streetmasters = StreetMaster.query();
        $scope.jobcarditemrequirements = JobCardItemRequirement.query();
        $scope.load = function(id) {
            WaterLeakageComplaint.get({id : id}, function(result) {
                $scope.waterLeakageComplaint = result;
            });
        };
        
        

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:waterLeakageComplaintUpdate', result);
            //$uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.waterLeakageComplaint.id != null) {
                WaterLeakageComplaint.update($scope.waterLeakageComplaint, onSaveSuccess, onSaveError);
            } else {
                WaterLeakageComplaint.save($scope.waterLeakageComplaint, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForComplaintDateTime = {};

        $scope.datePickerForComplaintDateTime.status = {
            opened: false
        };

        $scope.datePickerForComplaintDateTimeOpen = function($event) {
            $scope.datePickerForComplaintDateTime.status.opened = true;
        };
        
        
        
        $scope.datePickerForAssignedDate = {};

        $scope.datePickerForAssignedDate.status = {
            opened: false
        };

        $scope.datePickerForAssignedDateOpen = function($event) {
            $scope.datePickerForAssignedDate.status.opened = true;
        };
});
