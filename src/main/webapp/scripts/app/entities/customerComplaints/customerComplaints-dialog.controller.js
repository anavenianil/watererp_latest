'use strict';

angular.module('watererpApp')
    .controller('CustomerComplaintsDialogController', function ($scope, $state, CustomerComplaints, ParseLinks, $stateParams, ApplicationTxn, ComplaintTypeMaster) {
    $scope.customerComplaints = {};
    $scope.customerComplaints.complaintDate = new Date();////////  This code is to per populate system date in Complaint Date Field
    $scope.applicationtxns = ApplicationTxn.query();
    $scope.complainttypemasters = ComplaintTypeMaster.query();
    $scope.load = function(id) {
        CustomerComplaints.get({id : id}, function(result) {
            $scope.customerComplaints = result;
        });
    };
    
    if($stateParams.id!=null){        	
    	$scope.load($stateParams.id);
    }

    var onSaveSuccess = function (result) {
        $scope.$emit('watererpApp:customerComplaintsUpdate', result);
        //$uibModalInstance.close(result);
        $scope.isSaving = false;
        $state.go('customerComplaints');
    };

    var onSaveError = function (result) {
        $scope.isSaving = false;
    };

    $scope.save = function () {
        $scope.isSaving = true;
        if ($scope.customerComplaints.id != null) {
            CustomerComplaints.update($scope.customerComplaints, onSaveSuccess, onSaveError);
        } else {
            CustomerComplaints.save($scope.customerComplaints, onSaveSuccess, onSaveError);
        }
    };

    $scope.clear = function() {
        $uibModalInstance.dismiss('cancel');
    };
    $scope.datePickerForComplaintDate = {};

    $scope.datePickerForComplaintDate.status = {
        opened: false
    };

    $scope.datePickerForComplaintDateOpen = function($event) {
        $scope.datePickerForComplaintDate.status.opened = true;
    };
    
    $scope.getApplicationTxn = function(fileNo){
    	//alert("getApplicationTxn		getApplicationTxn		getApplicationTxn		getApplicationTxn");
    	ApplicationTxn.get({id : fileNo}, function(result) {
            $scope.applicationTxn = result; 
            $scope.customerComplaints.applicationTxn = {};
            $scope.customerComplaints.applicationTxn.street = $scope.applicationTxn.street;
        });	
    }
    
 });


/*'use strict';

angular.module('watererpApp').controller('CustomerComplaintsDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'CustomerComplaints', 'ApplicationTxn', 'ComplaintTypeMaster',
        function($scope, $stateParams, $uibModalInstance, entity, CustomerComplaints, ApplicationTxn, ComplaintTypeMaster) {

        $scope.customerComplaints = entity;
        $scope.applicationtxns = ApplicationTxn.query();
        $scope.complainttypemasters = ComplaintTypeMaster.query();
        $scope.load = function(id) {
            CustomerComplaints.get({id : id}, function(result) {
                $scope.customerComplaints = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:customerComplaintsUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.customerComplaints.id != null) {
                CustomerComplaints.update($scope.customerComplaints, onSaveSuccess, onSaveError);
            } else {
                CustomerComplaints.save($scope.customerComplaints, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
*/