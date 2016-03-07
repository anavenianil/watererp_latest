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
            //$scope.page = page;
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
            $scope.customer = {
                    requestDate: null,
                    firstName: null,
                    middleName: null,
                    lastName: null,
                    houseNo: null,
                    govtOfficialNo: null,
                    ward: null,
                    street: null,
                    pincode: null,
                    block: null,
                    area: null,
                    section: null,
                    constituency: null,
                    email: null,
                    telOffice: null,
                    telHome: null,
                    mobile: null,
                    id: null
                };
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
        
        //save method
        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:applicationTxnUpdate', result);
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
           $('#saveSuccessfullyModal').modal('show');
           $scope.clear();
        };
        
        //to get correspondence address when both are same
        $scope.getAddress = function(flag){
        	//alert(flag);
        	if(flag == true){
        		$scope.applicationTxn.sHouseNo = $scope.applicationTxn.customer.houseNo;
        		$scope.applicationTxn.street = $scope.applicationTxn.customer.street;
        		$scope.applicationTxn.area = $scope.applicationTxn.customer.area;
        		$scope.applicationTxn.govtOfficialNo = $scope.applicationTxn.customer.govtOfficialNo;
        		$scope.applicationTxn.pincode = $scope.applicationTxn.customer.pincode;
        		$scope.applicationTxn.section = $scope.applicationTxn.customer.section;
        		$scope.applicationTxn.ward = $scope.applicationTxn.customer.ward;
        		$scope.applicationTxn.block = $scope.applicationTxn.customer.block;
        		$scope.applicationTxn.constituency = $scope.applicationTxn.customer.constituency;
        		$scope.applicationTxn.telephoneNumber = $scope.applicationTxn.customer.telOffice;
        		$scope.applicationTxn.mobile = $scope.applicationTxn.customer.mobile;
        		$scope.applicationTxn.email = $scope.applicationTxn.customer.email;
        	}
        	else{
        		$scope.applicationTxn.sHouseNo = "";
        		$scope.applicationTxn.street = "";
        		$scope.applicationTxn.area = "";
        		$scope.applicationTxn.govtOfficialNo = "";
        		$scope.applicationTxn.pincode = "";
        		$scope.applicationTxn.section = "";
        		$scope.applicationTxn.ward = "";
        		$scope.applicationTxn.block = "";
        		$scope.applicationTxn.constituency = "";
        		$scope.applicationTxn.telephoneNumber = "";
        		$scope.applicationTxn.mobile = "";
        		$scope.applicationTxn.email = "";
        	}
        }
        
        $scope.sqMtsToYdsScanPlan = function(mts){
        	$scope.applicationTxn.scanPlan1 = mts*1.20;
        }
        
        $scope.sqYdsToSqMtsScanPlan = function(yds){
        	$scope.applicationTxn.scanPlan = yds * 0.84;
        }
        
        
        $scope.sqMtsToYdsSaleDeed = function(mts){
        	$scope.applicationTxn.saleDeed1 = mts*1.20;
        }
        
        $scope.sqYdsToSqMtsSaleDeed = function(yds){
        	$scope.applicationTxn.saleDeed = yds * 0.84;
        }
        
        
        
        
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
        
        //mydate
        
    });
