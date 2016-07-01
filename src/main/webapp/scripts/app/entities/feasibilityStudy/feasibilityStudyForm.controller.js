'use strict';

angular.module('watererpApp')
    .controller('FeasibilityStudyFormController', function ($scope, $state, FeasibilityStudy, SchemeMaster, TariffCategoryMaster, 
    		MakeOfPipe, MainWaterSize, MainSewerageSize, DocketCode, ApplicationTxn, CategoryMaster, SewerSize, PipeSizeMaster, 
    		ParseLinks) {

        $scope.feasibilityStudys = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 0;
        
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
        $scope.applicationtxn = {};
        
        $scope.getCustomer = function(fileNo){
        	ApplicationTxn.get({id : fileNo}, function(result) {
                $scope.applicationTxn = result;
                //$scope.feasibilityStudy.customerName = $scope.applicationTxn.firstName;
            });	
        }
        
        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:feasibilityStudyUpdate', result);
            //$uibModalInstance.close(result);
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
        },function(){
        	$scope.clear();
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
                id: null
            };
        };
    });
