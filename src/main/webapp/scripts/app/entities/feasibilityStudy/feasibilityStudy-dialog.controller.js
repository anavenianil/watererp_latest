'use strict';

angular.module('watererpApp')
    .controller('FeasibilityStudyDialogController', function ($scope, $stateParams, ParseLinks, $state, FeasibilityStudy, DivisionMaster,
    		StreetMaster, ApplicationTxn, User, ApplicationTxnService) {
    	
    	$scope.feasibilityStudy = {};
        $scope.divisionmasters = DivisionMaster.query();
        $scope.users = User.query();
        $scope.applicationTxn = {};
        
        if($stateParams.id != null){
        	$scope.feasibilityStudyId = $stateParams.id;
        	FeasibilityStudy.get({id : $scope.feasibilityStudyId}, function(result) {
                $scope.feasibilityStudy = result;
            });
        }
        
        $scope.maxDate = new Date();
        
        //get applicationTxn by id
        $scope.getApplicationTxn = function(fileNo){
        	ApplicationTxn.get({id : fileNo}, function(result) {
                $scope.applicationTxn = result;
                $scope.feasibilityStudy.applicationTxn = $scope.applicationTxn;
                $scope.feasibilityStudy.applicationTxn.id = $scope.applicationTxn.id;
                $scope.feasibilityStudy.tariffCategoryMaster = {};
                $scope.feasibilityStudy.tariffCategoryMaster.id = $scope.applicationTxn.tariffCategoryMaster.id;
            });	
        }
        
        if($stateParams.applicationId != null){
        	$scope.getApplicationTxn($stateParams.applicationId);
        }
        
        //get ApplicationTxn by status when page loaded
        
        $scope.getApplicationTxns = function(){
        	$scope.applicationTxns = [];
        	ApplicationTxn.query({page: $scope.page, status: 0}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.applicationTxns.push(result[i]);
                }
           });
        }
        $scope.getApplicationTxns();
        
        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:feasibilityStudyUpdate', result);
            $scope.isSaving = false;
            $state.go("applicationTxn");
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        
        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.feasibilityStudy.id != null) {
                FeasibilityStudy.update($scope.feasibilityStudy, onSaveSuccess, onSaveError);
            } else {
                FeasibilityStudy.save($scope.feasibilityStudy, onSaveSuccess, onSaveError, function () {
                	console.log("hello");
                });
            }
        };
        

        $scope.clear = function() {
            //$uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForCreatedDate = {};

        $scope.datePickerForCreatedDate.status = {
            opened: false
        };

        $scope.datePickerForCreatedDateOpen = function($event) {
            $scope.datePickerForCreatedDate.status.opened = true;
        };
        $scope.datePickerForModifiedDate = {};

        $scope.datePickerForModifiedDate.status = {
            opened: false
        };

        $scope.datePickerForModifiedDateOpen = function($event) {
            $scope.datePickerForModifiedDate.status.opened = true;
        };
        
        
        $scope.datePickerForPreparedDate = {};

        $scope.datePickerForPreparedDate.status = {
            opened: false
        };

        $scope.datePickerForPreparedDateOpen = function($event) {
            $scope.datePickerForPreparedDate.status.opened = true;
        };
        
        $scope.datePickerForZonalHeadApprovalDate = {};

        $scope.datePickerForZonalHeadApprovalDate.status = {
            opened: false
        };

        $scope.datePickerForZonalHeadApprovalDateOpen = function($event) {
            $scope.datePickerForZonalHeadApprovalDate.status.opened = true;
        };
        
        
        $scope.datePickerForDeptHeadInspectedDate = {};

        $scope.datePickerForDeptHeadInspectedDate.status = {
            opened: false
        };

        $scope.datePickerForDeptHeadInspectedDateOpen = function($event) {
            $scope.datePickerForDeptHeadInspectedDate.status.opened = true;
        };
        
        $scope.datePickerForOperationMangrapproveDate = {};

        $scope.datePickerForOperationMangrapproveDate.status = {
            opened: false
        };

        $scope.datePickerForOperationMangrapproveDateOpen = function($event) {
            $scope.datePickerForOperationMangrapproveDate.status.opened = true;
        };

        $scope.clear = function () {
            $scope.feasibilityStudy = {
                plotAreaInSqMtrs: null,
                plotAreaInYards: null,
                noOfFlatsOrNoOfUnits: null,
                noOfFloors: null,
                totalPlinthArea: null,
                waterRequirement: null,
                id: null,
                fileNo: null
            };

            $scope.applicationTxn = {
	            sHouseNo: null,
	           	street: null,
	           	areaCode: null,
	           	govtOfficialNo: null,
	           	pincode: null,
	           	area: null,
	           	telephoneNumber: null,
	           	mobile: null,
	           	section: null
            }
        };
        
        $scope.getStreet = function(divisionId){
        	$scope.streetMasters = [];
            StreetMaster.query({page: $scope.page, size: 20, divisionId: divisionId}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.streetMasters.push(result[i]);
                }
            });
        }
        
        $scope.getpreparedByName = function(obj){
        	$scope.feasibilityStudy.preparedBy.name = obj.firstName +" "+obj.lastName;
           }
        
        $scope.getZonalHeadName = function(obj){
        	$scope.feasibilityStudy.approvedByZonalHead.name = obj.firstName +" "+obj.lastName;
           }
        
        $scope.getDeparmentHeadName = function(obj){
        	$scope.feasibilityStudy.inspectionByDepartmentHead.name = obj.firstName +" "+obj.lastName;
           }
        
        $scope.getOperationManagerName = function(obj){
        	$scope.feasibilityStudy.approvedByOperationManager.name = obj.firstName +" "+obj.lastName;
           }
        
    
    
});

