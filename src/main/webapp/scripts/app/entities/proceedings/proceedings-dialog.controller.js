'use strict';

angular.module('watererpApp').controller('ProceedingsDialogController',
        function($scope, $state, $stateParams, Proceedings, PercentageMaster, ApplicationTxn, ItemRequired, MaterialMaster) {

        $scope.proceedings = {};
        $scope.percentagemasters = PercentageMaster.query();
        $scope.applicationtxns = ApplicationTxn.query();
        $scope.itemrequireds = ItemRequired.query();
        $scope.materialmasters = MaterialMaster.query();
        $scope.proceedings.itemRequired = {};
        
        
        
        $scope.load = function(id) {
            Proceedings.get({id : id}, function(result) {
                $scope.proceedings = result;
            });
        };
        
        if($stateParams.id != null){
        	$scope.load($stateParams.id);
        }

        var onSaveSuccess = function (result) {
            $scope.$emit('watererpApp:proceedingsUpdate', result);
            //$uibModalInstance.close(result);
            $scope.isSaving = false;
            $state.go('proceedings');
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.proceedings.id != null) {
                Proceedings.update($scope.proceedings, onSaveSuccess, onSaveError);
            } else {
                Proceedings.save($scope.proceedings, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        
        $scope.proceedings.itemRequireds = [];
        $scope.count = 0;
        $scope.itemArr = [];
        $scope.createItemArr = function(){
       		$scope.itemArr.push($scope.count);
       		$scope.proceedings.itemRequireds[$scope.count]= {};
       		$scope.count = $scope.count +1;
        }
        
        $scope.getMaterialDetail = function(id, indexId){
        	MaterialMaster.get({id : id}, function(result) {
                $scope.materialMaster = result;
                $scope.proceedings.itemRequired[indexId].ratePerShs = $scope.materialMaster.unitRate;
            });
        }
        
        $scope.calculateRate = function(quantity, rate, indexId){
        	$scope.proceedings.itemRequired[indexId].amount = quantity * rate; 
        }
        
});
