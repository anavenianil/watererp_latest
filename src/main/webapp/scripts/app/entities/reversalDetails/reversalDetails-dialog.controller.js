'use strict';

angular.module('watererpApp').controller('ReversalDetailsDialogController',
        function($scope, $stateParams, ReversalDetails, CollDetails, User, $state) {

        $scope.reversalDetails = {};
        $scope.colldetailss = CollDetails.query();
        $scope.users = User.query();
        $scope.load = function(id) {
            ReversalDetails.get({id : id}, function(result) {
                $scope.reversalDetails = result;
            });
        };
        
        if($stateParams.id != null){
        	$scope.load($stateParams.id);
        }

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:reversalDetailsUpdate', result);
            //$uibModalInstance.close(result);
            $scope.isSaving = false;
            $state.go('reversalDetails');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.reversalDetails.id != null) {
                ReversalDetails.update($scope.reversalDetails, onSaveSuccess, onSaveError);
            } else {
                ReversalDetails.save($scope.reversalDetails, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForCancelledDate = {};

        $scope.datePickerForCancelledDate.status = {
            opened: false
        };

        $scope.datePickerForCancelledDateOpen = function($event) {
            $scope.datePickerForCancelledDate.status.opened = true;
        };
});
