'use strict';

angular.module('watererpApp').controller('MeterChangeDialogController',
        function($scope, $state, $stateParams, MeterChange, CustDetails, MeterDetails, User) {

        $scope.meterChange = {};
        $scope.custdetailss = CustDetails.query();
        $scope.meterdetailss = MeterDetails.query();
        $scope.users = User.query();
        $scope.load = function(id) {
            MeterChange.get({id : id}, function(result) {
                $scope.meterChange = result;
            });
        };
        
        if($stateParams.id != null){
        	$scope.load($stateParams.id);
        }

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:meterChangeUpdate', result);
            //$uibModalInstance.close(result);
            $scope.isSaving = false;
            $state.go('meterChange');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.meterChange.id != null) {
                MeterChange.update($scope.meterChange, onSaveSuccess, onSaveError);
            } else {
                MeterChange.save($scope.meterChange, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForApprovedDate = {};

        $scope.datePickerForApprovedDate.status = {
            opened: false
        };

        $scope.datePickerForApprovedDateOpen = function($event) {
            $scope.datePickerForApprovedDate.status.opened = true;
        };
});
