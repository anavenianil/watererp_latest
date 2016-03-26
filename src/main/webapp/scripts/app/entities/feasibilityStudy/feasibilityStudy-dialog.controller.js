'use strict';

angular.module('watererpApp')
    .controller('FeasibilityStudyDialogController', function ($scope, $stateParams, ParseLinks, $state, FeasibilityStudy, DivisionMaster, ZoneMaster,
    		StreetMaster, ApplicationTxn, User, CategoryMaster) {
    	
    	$scope.feasibilityStudy = {};
        $scope.divisionmasters = DivisionMaster.query();
        //$scope.zonemasters = ZoneMaster.query();
        //$scope.streetmasters = StreetMaster.query();
        //$scope.applicationtxns = ApplicationTxn.query();
        $scope.users = User.query();
        $scope.categorymasters = CategoryMaster.query();
        $scope.applicationTxn = {};
        
        if($stateParams.id != null){
        	$scope.feasibilityStudyId = $stateParams.id;
        	FeasibilityStudy.get({id : $scope.feasibilityStudyId}, function(result) {
                $scope.feasibilityStudy = result;
            });
        }
        
        //get ApplicationTxn by status when page loaded
        $scope.applicationTxns = [];
        ApplicationTxn.query({page: $scope.page, status: 0}, function(result, headers) {
             $scope.links = ParseLinks.parse(headers('link'));
             for (var i = 0; i < result.length; i++) {
                 $scope.applicationTxns.push(result[i]);
             }
        });
        
        //get applicationTxn by id
        $scope.getApplicationTxn = function(fileNo){
        	ApplicationTxn.get({id : fileNo}, function(result) {
                $scope.applicationTxn = result;
                $scope.feasibilityStudy.applicationTxn = $scope.applicationTxn;
                $scope.feasibilityStudy.applicationTxn.id = $scope.applicationTxn.id;
            });	
        }
        /*$scope.load = function(id) {
            FeasibilityStudy.get({id : id}, function(result) {
                $scope.feasibilityStudy = result;
            });
        };*/

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:feasibilityStudyUpdate', result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.feasibilityStudy.id != null) {
                FeasibilityStudy.update($scope.feasibilityStudy, onSaveSuccess, onSaveError);
            } else {
                FeasibilityStudy.save($scope.feasibilityStudy, onSaveSuccess, onSaveError);
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
        $scope.datePickerForModifiedDate = {};

        $scope.datePickerForModifiedDate.status = {
            opened: false
        };

        $scope.datePickerForModifiedDateOpen = function($event) {
            $scope.datePickerForModifiedDate.status.opened = true;
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
        
        $scope.getZone = function(divisionId){
        	$scope.zoneMasters = [];
            ZoneMaster.query({page: $scope.page, size: 20, divisionId : divisionId}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.zoneMasters.push(result[i]);
                }
            });
        }
        
        $scope.getStreet = function(zoneId){
        	$scope.streetMasters = [];
            StreetMaster.query({page: $scope.page, size: 20, zoneId: zoneId}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.streetMasters.push(result[i]);
                }
            });
        }
        
    
    
});















/*'use strict';

angular.module('watererpApp').controller('FeasibilityStudyDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'FeasibilityStudy', 'DivisionMaster', 'ZoneMaster', 'StreetMaster', 'ApplicationTxn', 'User', 'CategoryMaster',
        function($scope, $stateParams, $uibModalInstance, entity, FeasibilityStudy, DivisionMaster, ZoneMaster, StreetMaster, ApplicationTxn, User, CategoryMaster) {

        $scope.feasibilityStudy = entity;
        $scope.divisionmasters = DivisionMaster.query();
        $scope.zonemasters = ZoneMaster.query();
        $scope.streetmasters = StreetMaster.query();
        $scope.applicationtxns = ApplicationTxn.query();
        $scope.users = User.query();
        $scope.categorymasters = CategoryMaster.query();
        $scope.load = function(id) {
            FeasibilityStudy.get({id : id}, function(result) {
                $scope.feasibilityStudy = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:feasibilityStudyUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.feasibilityStudy.id != null) {
                FeasibilityStudy.update($scope.feasibilityStudy, onSaveSuccess, onSaveError);
            } else {
                FeasibilityStudy.save($scope.feasibilityStudy, onSaveSuccess, onSaveError);
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
        $scope.datePickerForModifiedDate = {};

        $scope.datePickerForModifiedDate.status = {
            opened: false
        };

        $scope.datePickerForModifiedDateOpen = function($event) {
            $scope.datePickerForModifiedDate.status.opened = true;
        };
}]);
*/