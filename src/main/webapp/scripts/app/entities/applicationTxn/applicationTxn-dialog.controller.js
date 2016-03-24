'use strict';

angular.module('watererpApp')
    .controller('ApplicationTxnDialogController', function ($scope, $state, $stateParams ,ApplicationTxn, ParseLinks) {

    	$scope.applicationTxn = {};
    	
    	if($stateParams.id != null){
                ApplicationTxn.get({id : $stateParams.id}, function(result) {
                    $scope.applicationTxn = result;
                });
         }
    	
        /*$scope.load = function(id) {
            ApplicationTxn.get({id : id}, function(result) {
                $scope.applicationTxn = result;
            });
        };*/

        var onSaveSuccess = function (result) {
        	$scope.clear();
            $scope.$emit('watererpApp:applicationTxnUpdate', result);
            $scope.isSaving = false;
            $scope.applicationTxn.fileNumber = result.fileNumber;
            $scope.applicationTxn.id = result.id;
            $('#saveSuccessfullyModal').modal('show');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.applicationTxn.id != null) {
                ApplicationTxn.update($scope.applicationTxn, onSaveSuccess, onSaveError);
            } else {
                ApplicationTxn.save($scope.applicationTxn, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForRequestedDate = {};

        $scope.datePickerForRequestedDate.status = {
            opened: false
        };

        $scope.datePickerForRequestedDateOpen = function($event) {
            $scope.datePickerForRequestedDate.status.opened = true;
        };
        $scope.datePickerForCreatedDate = {};

        $scope.datePickerForCreatedDate.status = {
            opened: false
        };

        $scope.datePickerForCreatedDateOpen = function($event) {
            $scope.datePickerForCreatedDate.status.opened = true;
        };
        $scope.datePickerForUpdatedDate = {};

        $scope.datePickerForUpdatedDate.status = {
            opened: false
        };

        $scope.datePickerForUpdatedDateOpen = function($event) {
            $scope.datePickerForUpdatedDate.status.opened = true;
        };
        
        $scope.clear = function(){
        	$scope.applicationTxn = {fullName: null, homeOrOficeNumber: null, regionalNumber: null, faxNumber: null, plotNumber: null,
        			area: null, street: null,  villageExecutiveOffice: null,  villageExecutiveOfficeNumber: null, house: null,
        			institution: null, business: null, industry: null, poBox: null, requestedDate: null, photo: null, fileNumber: null,
        			createdDate: null, updatedDate: null, status: null, id: null
            };
        }
    });





/*'use strict';

angular.module('watererpApp').controller('ApplicationTxnDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'ApplicationTxn',
        function($scope, $stateParams, $uibModalInstance, entity, ApplicationTxn) {

        $scope.applicationTxn = entity;
        $scope.load = function(id) {
            ApplicationTxn.get({id : id}, function(result) {
                $scope.applicationTxn = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:applicationTxnUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.applicationTxn.id != null) {
                ApplicationTxn.update($scope.applicationTxn, onSaveSuccess, onSaveError);
            } else {
                ApplicationTxn.save($scope.applicationTxn, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForRequestedDate = {};

        $scope.datePickerForRequestedDate.status = {
            opened: false
        };

        $scope.datePickerForRequestedDateOpen = function($event) {
            $scope.datePickerForRequestedDate.status.opened = true;
        };
        $scope.datePickerForCreatedDate = {};

        $scope.datePickerForCreatedDate.status = {
            opened: false
        };

        $scope.datePickerForCreatedDateOpen = function($event) {
            $scope.datePickerForCreatedDate.status.opened = true;
        };
        $scope.datePickerForUpdatedDate = {};

        $scope.datePickerForUpdatedDate.status = {
            opened: false
        };

        $scope.datePickerForUpdatedDateOpen = function($event) {
            $scope.datePickerForUpdatedDate.status.opened = true;
        };
}]);
*/