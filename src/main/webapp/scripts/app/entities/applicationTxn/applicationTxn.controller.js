'use strict';

angular.module('watererpApp')
    .controller('ApplicationTxnController', function ($scope, $state, ApplicationTxn, ParseLinks, Customer, ApplicationTypeMaster, ConnectionTypeMaster, 
    		CtegoryMaster, SewerSize,FileNumber, PipeSizeMaster) {

        //$scope.applicationTxns = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        
        $scope.applicationtypemasters = ApplicationTypeMaster.query();
        $scope.connectiontypemasters = ConnectionTypeMaster.query();
        $scope.ctegorymasters = CtegoryMaster.query();
        $scope.pipesizemasters = PipeSizeMaster.query();
        $scope.sewersizes = SewerSize.query();
        $scope.filenumbers = FileNumber.query();
        $scope.applicationTxn = {};
        $scope.customer = {};
        
        
        $scope.loadAll = function() {
        	alert("hello");
        	$scope.applicationTxns = [];
        	$('#viewApplicationTxnModal').modal('show');
            ApplicationTxn.query({page: $scope.page, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.applicationTxns.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.applicationTxns = [];
            //$scope.loadAll();
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            //$scope.loadAll();
        };
        //$scope.loadAll();


        $scope.refresh = function () {
            $scope.reset();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.applicationTxn = {
                sHouseNo: null,
                govtOfficialNo: null,
                ward: null,
                street: null,
                pincode: null,
                block: null,
                area: null,
                section: null,
                constituency: null,
                email: null,
                telephoneNumber: null,
                mobile: null,
                scanPlan: null,
                scanPlan1: null,
                saleDeed: null,
                saleDeed1: null,
                totalPlinthArea: null,
                createdDate: null,
                updatedDate: null,
                status: null,
                id: null
            };
        };
        
        //save method
        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:applicationTxnUpdate', result);
            $scope.$emit('watererpApp:customerUpdate', result);
            //$uibModalInstance.close(result);
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
            if ($scope.customer.id != null) {
                Customer.update($scope.customer, onSaveSuccess, onSaveError);
            } else {
                Customer.save($scope.customer, onSaveSuccess, onSaveError);
            }
        };
        
        //
        /*var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:customerUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.customer.id != null) {
                Customer.update($scope.customer, onSaveSuccess, onSaveError);
            } else {
                Customer.save($scope.customer, onSaveSuccess, onSaveError);
            }
        };*/
        
        
        //date picker for customer Entity
        $scope.datePickerForRequestDate = {};

        $scope.datePickerForRequestDate.status = {
            opened: false
        };

        $scope.datePickerForRequestDateOpen = function($event) {
            $scope.datePickerForRequestDate.status.opened = true;
        };
        
        //date picker for ApplicationTxn Entity
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
    });
