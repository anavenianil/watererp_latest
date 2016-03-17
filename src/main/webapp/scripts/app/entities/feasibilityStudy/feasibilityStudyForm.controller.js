'use strict';

angular.module('watererpApp')
    .controller('FeasibilityStudyFormController', function ($scope, $state, FeasibilityStudy, SchemeMaster, TariffCategoryMaster, 
    		MakeOfPipe, MainWaterSize, MainSewerageSize, DocketCode, ApplicationTxn, CategoryMaster, SewerSize, PipeSizeMaster, 
    		FeasibilityStatus, ParseLinks, $stateParams, ItemDetails) {

        $scope.feasibilityStudys = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        $scope.feasibilityStudy = {};

        $scope.schememasters = SchemeMaster.query();
        $scope.tariffcategorymasters = TariffCategoryMaster.query();
        $scope.makeofpipes = MakeOfPipe.query();
        $scope.mainwatersizes = MainWaterSize.query();
        $scope.mainseweragesizes = MainSewerageSize.query();
        $scope.docketcodes = DocketCode.query();
        //$scope.applicationtxns = ApplicationTxn.query();
        $scope.categorymasters = CategoryMaster.query();
        $scope.sewersizes = SewerSize.query();
        $scope.pipesizemasters = PipeSizeMaster.query();
        $scope.feasibilitystatuss = FeasibilityStatus.query();
        $scope.itemDetailss = ItemDetails.query();
        $scope.applicationtxn = {};
        $scope.feasibilityStudy.applicationTxn = {};
        
        if($stateParams.feasibilityStudyId != null){
        	$scope.feasibilityStudyId = $stateParams.feasibilityStudyId;
        	FeasibilityStudy.get({id : $scope.feasibilityStudyId}, function(result) {
                $scope.feasibilityStudy = result;
            });
        }
        
        $scope.getCustomer = function(fileNo){
        	ApplicationTxn.get({id : fileNo}, function(result) {
                $scope.applicationTxn = result;
                $scope.feasibilityStudy.applicationTxn = $scope.applicationTxn;
                $scope.feasibilityStudy.applicationTxn.id = $scope.applicationTxn.id;
            });	
        }
        
        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:feasibilityStudyUpdate', result);
            //$uibModalInstance.close(result);
            $scope.isSaving = false;
            $state.go('feasibilityStudy');
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
        
        $scope.reset = function() {
            $scope.page = 0;
            $scope.feasibilityStudys = [];
            //$scope.loadAll();
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        //$scope.loadAll();


        $scope.refresh = function () {
            $scope.reset();
            //$scope.clear();
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
        
        
        $scope.feasibilityStudy.itemRequired = [];
        $scope.count = 0;
        $scope.itemArr = [];
        $scope.createItemArr = function(){
        	$scope.count = $scope.count +1;
       		$scope.itemArr.push($scope.count);
       		$scope.feasibilityStudy.itemRequired[$scope.count]= {};
       		//$scope.feasibilityStudy.itemRequired[$scope.count].feasibilityStudy = {};
       		$scope.feasibilityStudy.itemRequired[$scope.count].applicationTxn = {};
       		$scope.feasibilityStudy.itemRequired[$scope.count].applicationTxn.id = $scope.feasibilityStudy.applicationTxn.id;
        }
        
       /* $scope.items = function(){
        	for (var item in $scope.feasibilityStudy.itemRequired) {
        		$scope.feasibilityStudy.itemRequired.push($scope.feasibilityStudy.itemRequired[item]);
        	}
        }*/
        
    });
