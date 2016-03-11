'use strict';

angular.module('watererpApp').controller('FeasibilityStudyDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'FeasibilityStudy', 'SchemeMaster', 'TariffCategoryMaster', 'MakeOfPipe', 'MainWaterSize', 'MainSewerageSize', 'DocketCode', 'ApplicationTxn', 'CategoryMaster', 'SewerSize', 'PipeSizeMaster', 'FeasibilityStatus',
        function($scope, $stateParams, $uibModalInstance, entity, FeasibilityStudy, SchemeMaster, TariffCategoryMaster, MakeOfPipe, MainWaterSize, MainSewerageSize, DocketCode, ApplicationTxn, CategoryMaster, SewerSize, PipeSizeMaster, FeasibilityStatus) {

        $scope.feasibilityStudy = entity;
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
        $scope.applicationtxn = {};
        
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
        
        $scope.getCustomer = function(fileNo){
        	ApplicationTxn.get({id : fileNo}, function(result) {
                $scope.applicationTxn = result;
                //$scope.feasibilityStudy.customerName = $scope.applicationTxn.firstName;
            });
        	
        }
}]);
