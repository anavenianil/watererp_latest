'use strict';

angular.module('watererpApp').controller('ApplicationTxnDialogController',
    ['$scope', '$stateParams', /*'$uibModalInstance', 'entity',*/ 'ApplicationTxn', 'ApplicationTypeMaster', 'ConnectionTypeMaster', 'CategoryMaster', 'PipeSizeMaster', 'SewerSize', 'FileNumber', 'Customer','ParseLinks',
        function($scope, $stateParams, /*$uibModalInstance, entity,*/ ApplicationTxn, ApplicationTypeMaster, ConnectionTypeMaster, CategoryMaster, PipeSizeMaster, SewerSize, FileNumber, Customer, ParseLinks) {

        $scope.applicationTxn = {};//entity;
        $scope.applicationtypemasters = ApplicationTypeMaster.query();
        $scope.connectiontypemasters = ConnectionTypeMaster.query();
        $scope.categorymasters = CategoryMaster.query();
        $scope.pipesizemasters = PipeSizeMaster.query();
        $scope.sewersizes = SewerSize.query();
        $scope.filenumbers = FileNumber.query();
        $scope.customers = Customer.query();
        
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        
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
        
        $scope.loadAll = function() {
        	$scope.applicationTxns = [];
        	//$('#viewApplicationTxnModal').modal('show');
            ApplicationTxn.query({page: $scope.page, size: 100, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.applicationTxns.push(result[i]);
                }
            });
        };
        $scope.loadAll();
        
        $scope.onSearch = function(status) {
        	$scope.applicationTxns = [];
        	//$('#viewApplicationTxnModal').modal('show');
            ApplicationTxn.query({page: $scope.page, size: 20, status: status, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.applicationTxns.push(result[i]);
                }
            });
        };
}]);
