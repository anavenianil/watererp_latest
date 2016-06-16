'use strict';

angular.module('watererpApp').controller('AdjustmentsDialogController',
        function($scope, $stateParams,  Adjustments, CustDetails, BillFullDetails, TransactionTypeMaster, User, ComplaintTypeMaster, $state) {

        $scope.adjustments = {};
        $scope.custdetailss = CustDetails.query();
        $scope.billfulldetailss = BillFullDetails.query();
        $scope.transactiontypemasters = TransactionTypeMaster.query();
        $scope.users = User.query();
        $scope.complainttypemasters = ComplaintTypeMaster.query();
        
        $scope.load = function(id) {
            Adjustments.get({id : id}, function(result) {
                $scope.adjustments = result;
            });
        };

        if($stateParams.id!=null){
        	$scope.load($stateParams.id)
        }
        
        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:adjustmentsUpdate', result);
            //$uibModalInstance.close(result);
            $scope.isSaving = false;
            $scope.adjustments.id = result.id;
            $('#saveSuccessfullyModal').modal('show');
            //$state.go('adjustments');
            $scope.rc.editForm.attempted = false;
			$scope.editForm.$setPristine();
			//$scope.clear();
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.adjustments.id != null) {
                Adjustments.update($scope.adjustments, onSaveSuccess, onSaveError);
            } else {
                Adjustments.save($scope.adjustments, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            //$uibModalInstance.dismiss('cancel');
        	$scope.adjustments = {can:null, amount:null, user:null,txnTime:null, status:null,
        			transactionTypeMaster:null, complaintTypeMaster:null,  remarks:null };
        };
        $scope.datePickerForTxnTime = {};

        $scope.datePickerForTxnTime.status = {
            opened: false
        };

        $scope.datePickerForTxnTimeOpen = function($event) {
            $scope.datePickerForTxnTime.status.opened = true;
        };
});
